package com.tang.tensquare.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tang.tensquare.base.model.Label;
import com.tang.tensquare.base.service.LabelSevice;
import com.tang.tensquare.common.controller.BaseController;
import com.tang.tensquare.common.entity.Result;
import com.tang.tensquare.common.entity.Result.PageResult;
import com.tang.tensquare.common.entity.StatusCode;
import com.tang.tensquare.common.util.IdWorker;

@RestController
@RequestMapping("label")
@RefreshScope
public class LabelController extends BaseController {

	@Autowired
	private IdWorker idWorker;
	
	@Autowired
	private LabelSevice labelService;
	
	@Value("${test.test}")
	private String test;
	
	@PostMapping
	public Result save(@RequestBody Label label) {
		label.setId(idWorker.nextId() + "");
		labelService.save(label);
		return new Result(true, StatusCode.OK, "添加成功", null);
	}
	
	@GetMapping
	public Result getAll() {
		System.out.println(test);
		return new Result(true, StatusCode.OK, "查询成功", labelService.getAll());
	}
	
	@GetMapping("/toplist")
	public Result getTopList() {
		return new Result();
	}
	
	@GetMapping("/list")
	public Result validLabelList() {
		return new Result();
	}
	
	@GetMapping("/{labelId}")
	public Result getById(@PathVariable("labelId")String labelId) {
		return new Result(true, StatusCode.OK, "查询成功", labelService.getById(labelId));
	}
	
	@PutMapping("/{labelId}")
	public Result update(@PathVariable("labelId")String labelId, @RequestBody Label label) {
		label.setId(labelId);
		labelService.update(label);
		return new Result(true, StatusCode.OK, "更新成功", null);
	}
	
	@DeleteMapping("/{labelId}")
	public Result delete(@PathVariable("labelId")String labelId) {
		labelService.delete(labelId);
		return new Result(true, StatusCode.OK, "删除成功", null);
	}
	
	@PostMapping("/search/{page}/{size}")
	public Result searchByPage(@PathVariable("page")Integer page, @PathVariable("size")Integer size, @RequestBody Label label) {
		Page<Label> pageBean = labelService.searchByPage(label, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageBean.getTotalElements(), pageBean.getContent()));
	}
	
	@PostMapping("/search")
	public Result search(@RequestBody Label label) {
		return new Result(true, StatusCode.OK, "查询成功", labelService.search(label));
	}
	
	
}
