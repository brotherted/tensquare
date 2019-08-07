package com.tang.tensquare.friend.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tang.tensquare.common.controller.BaseController;
import com.tang.tensquare.common.entity.Result;
import com.tang.tensquare.common.entity.StatusCode;
import com.tang.tensquare.friend.service.FriendService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/friend")
public class FriendController extends BaseController {

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private HttpServletRequest request;
	
	@PutMapping("/like/{friendid}/{type}")
	public Result addFriendOrNoFriend(@PathVariable String friendid, @PathVariable String type) {
		Claims claims = (Claims) request.getAttribute("user_claims");
		String userid = claims.getId();
		/*if(StringUtils.isEmpty(type)||type.matches("^[^12]$")) {
			return new Result(false, StatusCode.ERROR, "params exception");
		}*/
		if(Objects.equals("1", type)) {
			int flag = friendService.addFriend(userid, friendid);
			if(flag == 1) {
				return new Result(true, StatusCode.OK, "添加好友成功");
			}
			if(flag == 0) {
				return new Result(false, StatusCode.REPERROR, "不能重复添加好友");
			}
		}else if(Objects.equals("2", type)) {
			int flag = friendService.addNoFriend(userid, friendid);
			if(flag == 1) {
				return new Result(true, StatusCode.OK, "添加非好友成功");
			}
			if(flag == 0) {
				return new Result(false, StatusCode.REPERROR, "不能重复添加非好友");
			}
		}else {
			return new Result(false, StatusCode.ERROR, "参数异常");
		}
		return null;
	}
	
	@DeleteMapping("/{friendid}")
	public Result deleteFriend(@PathVariable String friendid) {
		Claims claims = (Claims) request.getAttribute("user_claims");
		String userid = claims.getId();
		friendService.deleteFriend(userid, friendid);
		return new Result(true, StatusCode.OK, "success");
	}
}
