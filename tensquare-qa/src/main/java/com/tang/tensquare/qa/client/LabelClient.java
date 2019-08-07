package com.tang.tensquare.qa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tang.tensquare.common.entity.Result;

@FeignClient(value = "tensquare-base", fallback = LabelClientProtect.class)
public interface LabelClient {

	
	@GetMapping("/label")
	public Result getAll();
	
	@GetMapping("/label/toplist")
	public Result getTopList();
	
	@GetMapping("/label/list")
	public Result validLabelList();
	
	@GetMapping("/label/{labelId}")
	public Result getById(@PathVariable("labelId")String labelId);
	
}
