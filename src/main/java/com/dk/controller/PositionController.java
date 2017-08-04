package com.dk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.BindInfo;
import com.dk.object.PositionInfo;
import com.dk.object.PqiaData;
import com.dk.result.Result;
import com.dk.service.BindService;
import com.dk.service.PositonService;
import com.dk.util.myutils;

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
	//搜索根据N个条件去查询所有数据
	@RequestMapping("getinfoasframenum.ll")
	@ResponseBody
	public Result getInfoAsFrameNum(@RequestBody PqiaData info){
//		if ( (!StringUtils.isBlank(info.getVehicledefects()) || !StringUtils.isBlank(info.getNewspaperdefect()) || !StringUtils.isBlank(info.getRepairman())) ) {
//			Result result = new Result();
//			result = positionService.getInfoAsFrameNum1(info);
//			return result;
//		}
//		if (StringUtils.isBlank(info.getDevicenumber())) {
//			Result result = new Result();
//			result = positionService.getDeviceNumber(info);
//			return result;
//		}else
//		{
//			Result result = new Result();
//			result.setData(null);
//			result.setMessage("查询失败");
//			result.setStates(false);
//			return result;
//		}
//		 if(info.getDevicenumber() !=null && !info.getDevicenumber().isEmpty()){
//		Result result = new Result();
//		result = positionService.getDeviceNumber(info);
//		return result;
//		}else if ((info.getVehicledefects() !=null && !info.getVehicledefects().isEmpty()) || (info.getNewspaperdefect() != null && !info.getNewspaperdefect().isEmpty()) ||  (info.getRepairman() != null && !info.getRepairman().isEmpty() || (info.getVinnumber()!=null && !info.getVinnumber().isEmpty()))
//				||  ((info.getThistime()!=null && !info.getThistime().isEmpty()) && (info.getStatittime() !=null && !info.getStatittime().isEmpty())
//				&& (info.getEndtime() !=null && !info.getEndtime().isEmpty()))){
//			Result result = new Result();
//				result = positionService.getInfoAsFrameNum1(info);
//				return result;
//		}
//		Result result = new Result();
//		result.setData(null);
//		result.setMessage("查询失败");
//		result.setStates(false);
//		return result;
		if (myutils.mystring(info.getDevicenumber())) {
			Result result = new Result();
			result = positionService.getDeviceNumber(info);
			return result;
		}else if(((myutils.mystring(info.getDefectpeople()) && myutils.mystring(info.getNewspaperdefect())) || (myutils.mystring(info.getDefectcar()) && myutils.mystring(info.getVehicledefects()))
				|| (myutils.mystring(info.getRepairpeople())&& myutils.mystring(info.getRepairman())) || (myutils.mystring(info.getThistime()) && info.getEndtime()!=null && info.getStatittime()!=null) || myutils.mystring(info.getVinnumber()))&& !myutils.mystring(info.getDevicenumber())){
			Result result = new Result();
			result = positionService.getInfoAsFrameNum3(info);
			return result;
		}
		Result result = new Result();
		result.setData(null);
		result.setMessage("查询失败");
		result.setStates(false);
		return result;
	
	}
//	//搜索设备信息来查询有问题的车辆数据
//	@RequestMapping("getifdamagedcardata.ll")
//	@ResponseBody
//	public Result getifdamagedcardata(String Theinputdata){
//	   Result result=new Result();
//	   result=positionService.getifdamagedcardata(Theinputdata);
//		return result;
//	};
	
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
		if(info.getEquipmentNum()!=null&&!info.getEquipmentNum().isEmpty()){
			result = positionService.getInfoAsTime(info);
			return result;
		}
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
