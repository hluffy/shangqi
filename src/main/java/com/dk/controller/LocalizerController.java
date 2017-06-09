package com.dk.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

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
		info.setStaticTime("10");
		info.setRunTime("12*3600");
		info.setGpsTimeOut("3*60");
		info.setLoraSleepTime("3");
		result = localService.addInfo(info);
		return result;
	}
	
	@RequestMapping("updateinfo.ll")
	@ResponseBody
	public Result updateInfo(@RequestBody LocalizerInfo info){
		//修改服务器参数的方法
		//十进制转十六进制
//		StringTokenizer token=new StringTokenizer(info.getNumber(),".");  
//		List<Object> list=new ArrayList<>();
//        while(token.hasMoreElements()){ 
//        	int parseInt = Integer.parseInt(token.nextToken());
//        	String hexString = Integer.toHexString(parseInt); 
//        	list.add(hexString);
//        } 
//        //数组=数字
//        String str = "";
//        for(int i=0;i<list.size();i++){
//            str += list.get(i);
//        }
//        int parseInt = Integer.parseInt(info.getStaticTime());
//        int parseInt2 = Integer.parseInt(info.getRunTime());
//        int parseInt3 = Integer.parseInt(info.getGpsTimeOut());
//        int parseInt4 = Integer.parseInt(info.getLoraSleepTime());
//        StringBuffer setDate = new StringBuffer();
//        setDate.append("7B");//头信息
//        setDate.append("0004");//技术
//        setDate.append("414c545f4c6f5261303031");//sn
//        setDate.append("89");//指令
//        setDate.append("0008");//数据长度
//        setDate.append(str);//设备号
//        setDate.append(parseInt);//静止时间
//        setDate.append(parseInt2);//运动时间
//        setDate.append(parseInt3);//gps休眠时间
//        setDate.append(parseInt4);//lora休眠时间
//        setDate.append(str);//ip
//        setDate.append("7d");//包尾
//        System.out.println("发送："+setDate.toString());
	        
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
