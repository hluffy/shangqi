package com.dk.controller;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.User;
import com.dk.result.Result;
import com.dk.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("getinfos.ll")
	@ResponseBody
	public Result getUserInfos(){
		Result result = new Result();
		result = userService.getUserInfos();
		return result;
	}
	
	
	@RequestMapping("updateinfo.ll")
	@ResponseBody
	public Result updateUserInfo(@RequestBody User user){
		Result result = new Result();
		String role = user.getRole();
		if(role==null||role.isEmpty()){
			result.setMessage("用户角色不可以为空");
			result.setStates(false);
			return result;
		}
		if(!"op".equals(role)&&!"admin".equals(role)){
			result.setMessage("角色名只能为op或者admin");
			result.setStates(false);
			return result;
		}
		result = userService.updateUserInfo(user);
		return result;
	}
	
	@RequestMapping("addinfo.ll")
	@ResponseBody
	public Result addUserInfo(@RequestBody User user){
		String role = user.getRole();
		if(role==null||role.isEmpty()){
			user.setRole("op");
		}
		Result result = new Result();
		result = userService.addUserInfo(user);
		return result;
	}
	
	@RequestMapping("deleteinfo.ll")
	@ResponseBody
	public Result deleteUserInfo(@RequestBody User user){
		Result result = new Result();
		if("admin".equals(user.getUserName())){
			result.setStates(false);
			result.setMessage("该账号不可以删除!");
			return result;
		}
		result = userService.deleteUserInfo(user);
		return result;
	}
	
	@RequestMapping("logininfo.ll")
	@ResponseBody
	public Result loginUserInfo(@RequestBody User user,HttpServletResponse response){
		Result result = new Result();
		result = userService.loginUserInfo(user);
		Cookie cookie = new Cookie("username",user.getUserName());
		cookie.setPath("/");
		response.addCookie(cookie);
		return result;
	}
	
	@RequestMapping("getinfo.ll")
	@ResponseBody
	public Result getUserInfo(@RequestBody User info){
		Result result = new Result();
		if(info.getPage()==null){
			info.setPage(0);
		}
		result = userService.getUserInfo(info);
		return result;
	}

}
