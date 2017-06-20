package com.dk.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.EmployInfo;
import com.dk.result.Result;
import com.dk.service.EmployService;

@Controller
@RequestMapping("employ")
public class EmployController {
	@Resource
	private EmployService server;
	
	@RequestMapping("getinfos.ll")
	@ResponseBody
	public Result getInfos(){
		Result result = new Result();
		result = server.getInfos();
		return result;
	}
	
	@RequestMapping("addinfo.ll")
	@ResponseBody
	public Result addInfo(@RequestBody EmployInfo info){
		Result result = new Result();
		result = server.addInfo(info);
		return result;
	}
	
	@RequestMapping("updateinfo.ll")
	@ResponseBody
	public Result updateInfo(@RequestBody EmployInfo info){
		Result result = new Result();
		result = server.updateInfo(info);
		return result;
	}
	
	@RequestMapping("deleteinfo.ll")
	@ResponseBody
	public Result deleteInfo(@RequestBody EmployInfo info){
		Result result = new Result();
		result = server.deleteInfo(info);
		return result;
	}
	
	@RequestMapping("getinfo.ll")
	@ResponseBody
	public Result getInfo(@RequestBody EmployInfo info){
		Result result = new Result();
		result = server.getInfo(info);
		return result;
	}
	
	@RequestMapping("leadin.ll")
	@ResponseBody
	public Result leadIn(@RequestBody EmployInfo info){
		Result result = new Result();
		return result;
	}

}
