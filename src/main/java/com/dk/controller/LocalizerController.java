package com.dk.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.LocalizerInfo;
import com.dk.result.Result;
import com.dk.service.LocalizerService;

@Controller
@RequestMapping("local")
public class LocalizerController {
	@Resource
	private LocalizerService localService;
	
	@RequestMapping("getinfos.ll")
	@ResponseBody
	public Result getInfos(){
		Result result = new Result();
		result = localService.getInfos();
		return result;
	}
	
	@RequestMapping("addinfo.ll")
	@ResponseBody
	public Result addInfo(@RequestBody LocalizerInfo info){
		Result result = new Result();
		if(info.getStaticTime()==null||info.getStaticTime().isEmpty()){
			info.setStaticTime("5");
		}
		if(info.getRunTime()==null||info.getRunTime().isEmpty()){
			info.setRunTime("5");
		}
		result = localService.addInfo(info);
		return result;
	}
	
	@RequestMapping("updateinfo.ll")
	@ResponseBody
	public Result updateInfo(@RequestBody LocalizerInfo info){
		Result result = new Result();
		result = localService.updateInfo(info);
		return result;
	}
	
	@RequestMapping("deleteinfo.ll")
	@ResponseBody
	public Result deleteInfo(@RequestBody LocalizerInfo info){
		Result result = new Result();
		result = localService.deleteInfo(info);
		return result;
	}
	
	@RequestMapping("getinfo.ll")
	@ResponseBody
	public Result getInfo(@RequestBody LocalizerInfo info){
		Result result = new Result();
		result = localService.getInfo(info);
		return result;
	}
	
	@RequestMapping("getareadatas.ll")
	@ResponseBody
	public Result getAreaData(){
		Result result = new Result();
		result = localService.getAreaData();
		return result;
	}
	
	@RequestMapping("getinfoofarea.ll")
	@ResponseBody
	public Result getInfoOfArea(@RequestBody LocalizerInfo info){
		Result result = new Result();
		result = localService.getInfoOfArea(info);
		return result;
	}
	
	@RequestMapping("getequippie.ll")
	@ResponseBody
	public Result getEquipPie(){
		Result result = new Result();
		result = localService.getEquipInfoForPie();
		return result;
	}
	
	//设备电量信息饼图
	@RequestMapping("lowinfo.ll")
	@ResponseBody
	public Result getLowInfo(){
		Result result = new Result();
		result = localService.lowInfo();
		return result;
	}

}
