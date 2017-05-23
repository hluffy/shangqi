package com.dk.service;

import com.dk.object.CarInfo;
import com.dk.result.Result;

public interface CarService {
	Result getCarInfos();
	Result updateCarInfo(CarInfo info);
	Result getCarInfo(CarInfo info);
	Result addCarInfo(CarInfo info);
	Result deleteCarInfo(CarInfo info);

}
