package com.tang.tensquare.user.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tang.tensquare.common.controller.BaseController;
import com.tang.tensquare.common.entity.Result;
import com.tang.tensquare.common.entity.Result.PageResult;
import com.tang.tensquare.common.util.JwtUtil;
import com.tang.tensquare.common.entity.StatusCode;
import com.tang.tensquare.user.model.User;
import com.tang.tensquare.user.service.UserService;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	@PutMapping("/update/{userid}/{friendid}/{x}")
	public void updateFanscountAndFollowcount(@PathVariable String userid, @PathVariable String friendid, @PathVariable int x) {
		userService.updateFanscountAndFollowcount(userid, friendid, x);
	}
	
	
	@PostMapping("/register/{code}")
	public Result registry(@RequestBody User user, @PathVariable String code) {
		userService.registry(user, code);
		return new Result(true,StatusCode.OK,"success");
	}
	
	@PostMapping("/sendsms/{mobile}")
	public Result sendsms(@PathVariable String mobile) {
		userService.sendsms(mobile);
		return new Result(true,StatusCode.OK,"成功");
	}
	
	@PostMapping("/login")
	public Result login(@RequestBody Map<String, String> loginMap) {
		User user = userService.login(loginMap.get("mobile"), loginMap.get("password"));
		String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		map.put("user", user.getNickname());
		return new Result(true, StatusCode.OK, "success", map);
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
