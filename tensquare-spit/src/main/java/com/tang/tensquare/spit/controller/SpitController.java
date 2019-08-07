package com.tang.tensquare.spit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tang.tensquare.common.controller.BaseController;
import com.tang.tensquare.common.entity.Result;
import com.tang.tensquare.common.entity.Result.PageResult;
import com.tang.tensquare.common.entity.StatusCode;
import com.tang.tensquare.spit.model.Spit;
import com.tang.tensquare.spit.service.SpitService;

@RequestMapping("spit")
@RestController
public class SpitController extends BaseController {

	@Autowired
	private SpitService spitService;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@GetMapping
	public Result findAll() {
		return new Result(true, StatusCode.OK, "success", spitService.findAll());
	}

	@PostMapping
	public Result save(@RequestBody Spit spit) {
		spitService.save(spit);
		return new Result(true, StatusCode.OK, "success", null);
	}

	@PutMapping("/{id}")
	public Result update(@PathVariable("id") String id, @RequestBody Spit spit) {
		spit.set_id(id);
		spitService.update(spit);
		return new Result(true, StatusCode.OK, "success", null);
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable("id") String id) {
		spitService.delete(id);
		return new Result(true, StatusCode.OK, "success", null);
	}

	@PostMapping("/search")
	public Result search(@RequestBody Spit spit) {
		return new Result(true, StatusCode.OK, "success", spitService.search(spit));
	}

	@PostMapping("/search/{page}/{size}")
	public Result search(@RequestBody Spit spit, @PathVariable int page, @PathVariable int size) {
		Page<Spit> pageList = spitService.search(spit, page, size);
		return new Result(true, StatusCode.OK, "success", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
	}
	
	@GetMapping("/comment/{parentid}/{page}/{size}")
	public Result findByparentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
		Page<Spit> pageList = spitService.findByParenid(parentid, page, size);
		return new Result(true, StatusCode.OK, "success", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
	}
	
	@PutMapping("/thumbup/{id}")
	public Result updateThumbUp(@PathVariable String id) {
		String userid = "100";
		if(null == redisTemplate.opsForValue().get(userid)) {
			spitService.updateThumbUp(id);
			redisTemplate.opsForValue().set(userid, 1);
			return new Result(true, StatusCode.OK, "success", null);
		}
		return new Result(false, StatusCode.REPERROR, "不能重复点赞", null);
	}
}
