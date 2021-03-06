package com.dk.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.LoraInfo;
import com.dk.result.Result;
import com.dk.service.LoraService;

@Controller
@RequestMapping("lora")
public class LoraController {
	@Resource
	private LoraService loraService;
	
	@RequestMapping("getinfos.ll")
	@ResponseBody
	public Result getInfos(){
		Result result = new Result();
		result = loraService.getInfos();
		return result;
	}
	
//	@RequestMapping("updateinfo.ll")
//	@ResponseBody
//	public Result updateInfo(@RequestBody LoraInfo info){
//		Result result = new Result();
//		result = loraService.updateInfo(info);
//		return result;
//	}
	
	@RequestMapping("addinfo.ll")
	@ResponseBody
	public Result addInfo(@RequestBody LoraInfo info){
		Result result = new Result();
		if(info.getNumber()==null||info.getNumber().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不允许为空");
			return result;
		}
		LoraInfo queryInfo = new LoraInfo();
		queryInfo.setNumber(info.getNumber());
		queryInfo.setPage(0);
		result = loraService.getInfo(queryInfo);
		if(result.getCount()!=0){
			result.setStates(false);
			result.setMessage("该设备号已存在");
			return result;
		}
		result = loraService.addInfo(info);
		return result;
	}
	
	@RequestMapping("deleteinfo.ll")
	@ResponseBody
	public Result deleteInfo(@RequestBody LoraInfo info){
		Result result = new Result();
		result = loraService.deleteInfo(info);
		return result;
	}
	
	@RequestMapping("getinfo.ll")
	@ResponseBody
	public Result getInfo(@RequestBody LoraInfo info){
		Result result = new Result();
		if(info.getPage()==null){
			info.setPage(0);
		}
		result = loraService.getInfo(info);
		return result;
	}

}
