package com.dk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.IbeaconInfo;
import com.dk.result.Result;
import com.dk.service.IbeaconService;
import com.dk.serviceImpl.IbeaconServiceImpl;

@Controller
@RequestMapping("ibeacon")
public class IbeaconController {
	@Resource
	private IbeaconService ibeaconService;
	
	
	@RequestMapping("getinfos.ll")
	@ResponseBody
	public Result getInfos(){
		Result result = new Result();
		result = ibeaconService.getInfos();
		return result;
	}
	
	@RequestMapping("updateinfo.ll")
	@ResponseBody
	public Result updateInfo(@RequestBody IbeaconInfo info){
		Result result = new Result();
		result = ibeaconService.updateInfo(info);
		return result;
	}
	
	@RequestMapping("addinfo.ll")
	@ResponseBody
	public Result addInfo(@RequestBody IbeaconInfo info){
		Result result = new Result();
		result = ibeaconService.addInfo(info);
		return result;
	}
	
	@RequestMapping("deleteinfo.ll")
	@ResponseBody
	public Result deleteInfo(@RequestBody IbeaconInfo info){
		Result result = new Result();
		result = ibeaconService.deleteInfo(info);
		return result;
	}
	
	@RequestMapping("getinfo.ll")
	@ResponseBody
	public Result getIbeaconInfo(@RequestBody IbeaconInfo info){
		Result result = new Result();
		if(info.getPage()==null){
			info.setPage(0);
		}
		result = ibeaconService.getInfo(info);
		return result;
	}
	
	@RequestMapping("getinbeaconmap.ll")
	@ResponseBody
	public Result getIbeaconForMap(){
		Result result = new Result();
		result = ibeaconService.getIbeaconForMap();
		return result;
	}

}
