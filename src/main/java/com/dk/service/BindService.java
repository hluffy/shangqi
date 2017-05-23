package com.dk.service;

import com.dk.object.BindInfo;
import com.dk.result.Result;

public interface BindService {
	Result getBindInfos();
	Result addBindInfo(BindInfo info);
	Result updateBindInfo(BindInfo info);
	Result getBindInfo(BindInfo info);
	Result getBindInfoForCharts();

}
