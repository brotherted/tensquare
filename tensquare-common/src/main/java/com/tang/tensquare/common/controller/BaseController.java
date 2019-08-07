package com.tang.tensquare.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tang.tensquare.common.entity.Result;
import com.tang.tensquare.common.entity.StatusCode;


public class BaseController {

	@ExceptionHandler({Throwable.class})
	@ResponseBody
	public Result handleThrowable(Throwable ex, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result(false, StatusCode.ERROR, ex.getMessage());
		return result;
	}
}
