package com.tang.tensquare.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Aspect
@Component
public class CheckLoginAspect {
	
	@Pointcut("execution(* com.tang.tensquare.**.controller.*.save*(..))")
	public void save() {};
	
	@Pointcut("execution(* com.tang.tensquare.**.controller.*.add*(..))")
	public void add() {};
	
	@Pointcut("execution(* com.tang.tensquare.**.controller.*.update*(..))")
	public void update() {};
	
	@Pointcut("execution(* com.tang.tensquare.**.controller.*.delete*(..))")
	public void delete() {};
	
	@Autowired
	private HttpServletRequest request;
	
	@Before(value = "save()||add()||update()||delete()")
	public void check() {
		Claims loginClaims = (Claims) request.getAttribute("loginUser");
		if(loginClaims == null) {
			throw new RuntimeException("请先登录系统");
		}
	}
}
