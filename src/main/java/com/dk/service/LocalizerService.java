package com.dk.service;

import com.dk.object.LocalizerInfo;
import com.dk.result.Result;

public interface LocalizerService {
	Result getInfos();
	Result addInfo(LocalizerInfo info);
	//更新定位器静止时间和运行时间
	Result updateInfo(LocalizerInfo info);
	Result deleteInfo(LocalizerInfo info);
	Result getInfo(LocalizerInfo info);
	Result getAreaData();
	Result getInfoOfArea(LocalizerInfo info);
	Result getEquipInfoForPie();
	//更新定位器区域和电量
	Result updateLocalInfo(LocalizerInfo info);
	Result lowInfo();

}
