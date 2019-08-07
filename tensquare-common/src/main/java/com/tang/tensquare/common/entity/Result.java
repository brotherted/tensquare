package com.tang.tensquare.common.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

	private Boolean flag;
	private Integer code;
	private String message;
	private Object data;
	
	public Result(Boolean flag, Integer code, String message) {
		super();
		this.flag = flag;
		this.code = code;
		this.message = message;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PageResult<T> {
		private Long total;
		private List<T>	rows;
	}

}


