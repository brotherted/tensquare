package com.tang.tensquare.qa.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tang.tensquare.common.entity.Result;
import com.tang.tensquare.common.entity.StatusCode;

@Component
public class LabelClientProtect implements LabelClient {
	
	@GetMapping
	public Result getAll() {
		return new Result(false, StatusCode.ERROR, "服务繁忙，请稍后重试");
	}
	
	@GetMapping("/toplist")
	public Result getTopList() {
		return new Result(false, StatusCode.ERROR, "服务繁忙，请稍后重试");
	}
	
	@GetMapping("/list")
	public Result validLabelList() {
		return new Result(false, StatusCode.ERROR, "服务繁忙，请稍后重试");
	}
	
	@GetMapping("/{labelId}")
	public Result getById(@PathVariable("labelId")String labelId) {
		return new Result(false, StatusCode.ERROR, "服务繁忙，请稍后重试");
	}
	
}
