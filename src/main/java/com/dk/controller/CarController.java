package com.dk.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.object.CarInfo;
import com.dk.result.Result;
import com.dk.service.CarService;

@Controller
@RequestMapping("car")
public class CarController 	{
	@Resource
	private CarService carService;
	
	@RequestMapping("getinfos.ll")
	@ResponseBody
	public Result getCarInfos(){
		Result result = new Result();
		result = carService.getCarInfos();
		return result;
	}
	
	@RequestMapping("updateinfo.ll")
	@ResponseBody
	public Result updateCarInfo(@RequestBody CarInfo carInfo){
		Result result = new Result();
		result = carService.updateCarInfo(carInfo);
		return result;
	}
	
	@RequestMapping("getinfo.ll")
	@ResponseBody
	public Result getCarInfo(@RequestBody CarInfo info){
		Result result = new Result();
		if(info.getPage()==null){
			info.setPage(0);
		}
		result = carService.getCarInfo(info);
		return result;
	}
	
	@RequestMapping("addinfo.ll")
	@ResponseBody
	public Result addCarInfo(@RequestBody CarInfo info){
		Result result = new Result();
		result = carService.addCarInfo(info);
		return result;
	}
	
	@RequestMapping("deleteinfo.ll")
	@ResponseBody
	public Result deleteCarInfo(@RequestBody CarInfo info){
		Result result = new Result();
		result = carService.deleteCarInfo(info);
		return result;
	}

}
