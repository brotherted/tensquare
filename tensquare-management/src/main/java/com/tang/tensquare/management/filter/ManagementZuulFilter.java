package com.tang.tensquare.management.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tang.tensquare.common.entity.Result;
import com.tang.tensquare.common.entity.StatusCode;
import com.tang.tensquare.common.util.JwtUtil;

import io.jsonwebtoken.Claims;

@Component
public class ManagementZuulFilter extends ZuulFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("-----------------==经过过滤器----------------------------------");
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String authorization = request.getHeader("Authorization");
		if(request.getRequestURI().contains("login")) {
			return "success";
		}
		if(!StringUtils.isEmpty(authorization)) {
			if(authorization.contains("Bearer")) {
				String token = authorization.substring(7);
				try {
					Claims jwt = jwtUtil.parseJWT(token );
					if(Objects.equal("admin", jwt.get("roles"))){
						requestContext.addZuulRequestHeader("Authorization", authorization);
					}
				} catch (Exception e) {
					Result result = new Result(false, StatusCode.ACCESSERROR, "token格式错误");
					write(false, result);
				}
			}
		}else {
			Result result = new Result(false, StatusCode.ACCESSERROR, "请使用管理员身份登录");
			write(false, result);
		}
		return "success";
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	
	private void write(Boolean flag, Object obj) {
		try {
			RequestContext requestContext = RequestContext.getCurrentContext();
			requestContext.setSendZuulResponse(flag);
			requestContext.getResponse().setStatus(403);
			requestContext.getResponse().setContentType("application/json;charset=utf-8");
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			requestContext.getResponse().getWriter().print(mapper.writeValueAsString(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
