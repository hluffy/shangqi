package com.dk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.BindInfo;
import com.dk.object.PositionInfo;
import com.dk.result.Result;
import com.dk.service.BindService;
import com.dk.service.PositonService;

@Controller
@RequestMapping("position")
public class PositionController {
	@Resource
	private PositonService positionService;
	
	@Resource
	private BindService bindService;
	
	@RequestMapping("getinfos.ll")
	@ResponseBody
	public Result getInfos(){
		Result result = new Result();
		result = positionService.getInfos();
		return result;
	}
	
	@RequestMapping("getinfoasframenum.ll")
	@ResponseBody
	public Result getInfoAsFrameNum(String frameNum){
		Result result = new Result();
		result = positionService.getInfoAsFrameNum(frameNum);
		return result;
	}
	
	//根据车架号查找经纬度
	@SuppressWarnings("unchecked")
	@RequestMapping("getpositioninfo.ll")
	@ResponseBody
	public Result getPositionInfo(@RequestBody BindInfo bindInfo){
		Result result = new Result();
		bindInfo.setBindType("绑定");
		Object data = bindService.getBindInfo(bindInfo).getData();
		List<BindInfo> bindList = (ArrayList<BindInfo>)data;
		if(bindList.size()==0){
			result.setStates(false);
			result.setMessage("该车架号没有绑定的定位器信息");
			return result;
		}else{
			PositionInfo info = new PositionInfo();
			info.setEquipmentNum(bindList.get(0).getEquipmentNum());
			info.setPage(1);
			info.setTotal(1);
			result = positionService.getInfo(info);
		}
		return result;
	}
	
	@RequestMapping("getinfo.ll")
	@ResponseBody
	public Result getInfo(@RequestBody PositionInfo info){
		Result result = new Result();
		result = positionService.getInfo(info);
		return result;
	}
	
	@RequestMapping("getinfoforpie.ll")
	@ResponseBody
	public Result getInfoForPie(){
		Result result = new Result();
		result = positionService.getInfoForPie();
		return result;
	}
	
	@RequestMapping("addinfo.ll")
	@ResponseBody
	public Result addInfo(@RequestBody PositionInfo info){
		Result result = new Result();
		result = positionService.addInfo(info);
		return result;
	}
	
	@RequestMapping("updateinfo.ll")
	@ResponseBody
	public Result updateInfo(@RequestBody PositionInfo info){
		Result result = new Result();
		result = positionService.updateInfo(info);
		return result;
	}
	
	@RequestMapping("deleteinfo.ll")
	@ResponseBody
	public Result deleteInfo(@RequestBody PositionInfo info){
		Result result = new Result();
		result = positionService.deleteInfo(info);
		return result;
	}
	
	@RequestMapping("getlowinfo.ll")
	@ResponseBody
	public Result getLowInfo(){
		Result result = new Result();
		result = positionService.getInfoLow();
		return result;
	}
	
	//根据时间查询位置信息
	@SuppressWarnings("unchecked")
	@RequestMapping("getinfoastime.ll")
	@ResponseBody
	public Result getInfoAsTime(@RequestBody PositionInfo info){
		Result result = new Result();
		BindInfo bInfo = new BindInfo();
		bInfo.setFrameNum(info.getFrameNum());
		bInfo.setBindType("绑定");
		bInfo.setPage(0);
		Object data = bindService.getBindInfo(bInfo).getData();
		List<BindInfo> bindList = (ArrayList<BindInfo>)data;
		if(bindList.size()==0){
			result.setStates(false);
			result.setMessage("该车架号没有绑定的定位器信息");
			return result;
		}else{
			info.setEquipmentNum(bindList.get(0).getEquipmentNum());
			result = positionService.getInfoAsTime(info);
		}
		return result;
	}

}
