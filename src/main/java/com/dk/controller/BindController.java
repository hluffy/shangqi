package com.dk.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.BindInfo;
import com.dk.object.LocalizerInfo;
import com.dk.result.Result;
import com.dk.service.BindService;
import com.dk.service.LocalizerService;

@Controller
@RequestMapping("bind")
public class BindController {
	@Resource
	private BindService bindService;
	
	@Resource
	private LocalizerService localService;
	
	
	@RequestMapping("getinfos.ll")
	@ResponseBody
	public Result getInfos(){
		Result result = new Result();
		result = bindService.getBindInfos();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("addinfo.ll")
	@ResponseBody
	public Result addInfo(BindInfo info){
		Result result = new Result();
		
		//查询设备信息
		if(info.getEquipmentNum()==null||info.getEquipmentNum().isEmpty()
				||info.getFrameNum()==null||info.getFrameNum().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		LocalizerInfo localInfo = new LocalizerInfo();
		localInfo.setNumber(info.getEquipmentNum());
		Object data = localService.getInfo(localInfo).getData();
		List<LocalizerInfo> infoList = (ArrayList<LocalizerInfo>)data;
		if(infoList.size()==0){
			result.setStates(false);
			result.setMessage("设备号不存在");
			return result;
		}
		
		//查询车辆信息
//		info.getFrameNum();
		
		String bindType = info.getBindType();
		if("绑定".equals(bindType)){
			//查询该设备是否已绑定其他车辆
			BindInfo queryInfo = new BindInfo();
			queryInfo.setEquipmentNum(info.getEquipmentNum());
			queryInfo.setBindType("绑定");
			queryInfo.setPage(0);
			Object bindInfos = bindService.getBindInfo(queryInfo).getData();
			List<BindInfo> infos = (ArrayList<BindInfo>)bindInfos;
			if(infos.size()>0){
				result.setStates(false);
				result.setMessage("该设备已绑定");
				return result;
			}
			info.setBindTime(new Timestamp(System.currentTimeMillis()));
		}
		BindInfo queryInfo = new BindInfo();
		queryInfo.setFrameNum(info.getFrameNum());
		queryInfo.setEquipmentNum(info.getEquipmentNum());
		queryInfo.setPage(0);
		Object bindInfos = bindService.getBindInfo(queryInfo).getData();
		List<BindInfo> infos = (ArrayList<BindInfo>)bindInfos;
		if(infos.size()>0){
			result.setStates(false);
			BindInfo resultInfo = infos.get(0);
			if("绑定".equals(resultInfo.getBindType())){
				result.setMessage("该设备与该车架号已经绑定,无需重复绑定");
			}
			if("解绑".equals(resultInfo.getBindType())){
				result.setMessage("该设备与该车架号已经解绑,无法再绑定");
			}
			return result;
		}
		info.setBindTime(new Timestamp(System.currentTimeMillis()));
		
		result = bindService.addBindInfo(info);
		return result;
	}
	
//	@RequestMapping("updateinfo.ll")
//	@ResponseBody
//	public Result updateInfo(@RequestBody BindInfo info){
//		Result result = new Result();
//		String bindType =info.getBindType();
//		if("解绑".equals(bindType)){
//			result.setStates(false);
//			result.setData(info);
//			result.setMessage("已经解绑，不需要重复解绑");
//			return result;
//		}
//		info.setUnbindTime(new Timestamp(System.currentTimeMillis()));
//		info.setBindType("解绑");
//		result = bindService.updateBindInfo(info);
//		return result;
//	}
	
	@RequestMapping("updateinfo.ll")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Result updateInfo(BindInfo info){
		Result result = new Result();
		BindInfo bInfo = new BindInfo();
		bInfo.setEquipmentNum(info.getEquipmentNum());
//		bInfo.setFrameNum(info.getFrameNum());
		bInfo.setPage(0);
		bInfo.setBindType("绑定");
		result = bindService.getBindInfo(bInfo);
		List<BindInfo> infos = ((ArrayList<BindInfo>)(result.getData()));
		if(infos==null||infos.size()==0){
			result = new Result();
			result.setStates(false);
			result.setMessage("该绑定数据不存在，请核对设备编号或车架号");
			result.setData(null);
			return result;
		}
//		if("解绑".equals(infos.get(0).getBindType())){
//			result = new Result();
//			result.setStates(false);
//			result.setData(result.getData());
//			result.setMessage("已经解绑,不需要重复解绑");
//			return result;
//		}
		result = new Result();
		info = infos.get(0);
		info.setUnbindTime(new Timestamp(System.currentTimeMillis()));
		info.setBindType("解绑");
		result = bindService.updateBindInfo(info);
		return result;
	}
	
	@RequestMapping("getinfo.ll")
	@ResponseBody
	public Result getInfo(@RequestBody BindInfo info){
		Result result = new Result();
		if(info.getPage()==null){
			info.setPage(0);
		}
		if("解绑".equals(info.getBindType())){
			info.setUnbindStartTime(info.getBindStartTime());
			info.setUnbindEndTime(info.getBindEndTime());
			info.setBindStartTime("");
			info.setBindEndTime("");
		}
		result = bindService.getBindInfo(info);
		return result;
	}
	
	@RequestMapping("getinfoforcharts.ll")
	@ResponseBody
	public Result getInfoForCharts(){
		Result result = new Result();
		result = bindService.getBindInfoForCharts();
		return result;
	}

}
