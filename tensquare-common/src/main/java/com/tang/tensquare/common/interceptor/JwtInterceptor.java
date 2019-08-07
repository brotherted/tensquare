package com.tang.tensquare.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tang.tensquare.common.util.JwtUtil;

import io.jsonwebtoken.Claims;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String authorization = (String) request.getHeader("Authorization");
		if(!StringUtils.isEmpty(authorization)) {
			String token = authorization.substring(7);
			try {
				Claims jwt = jwtUtil.parseJWT(token);
				request.setAttribute("loginUser", jwt);
			} catch (Exception e) {
				throw new RuntimeException("token过期或有误");
			}
		}
		return true;
	}
}
