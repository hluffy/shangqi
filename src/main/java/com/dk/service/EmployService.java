package com.dk.service;

import com.dk.object.EmployInfo;
import com.dk.result.Result;

public interface EmployService {
	Result getInfos();
	Result addInfo(EmployInfo info);
	Result updateInfo(EmployInfo info);
	Result deleteInfo(EmployInfo info);
	Result getInfo(EmployInfo info);

}
