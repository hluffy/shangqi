package com.dk.service;

import com.dk.object.PositionInfo;
import com.dk.object.PqiaData;
import com.dk.result.Result;

public interface PositonService {
	Result getInfos();
	Result getInfoAsFrameNum(PqiaData info);
	Result getInfo(PositionInfo info);
	
	Result getInfoForPie();
	
	Result addInfo(PositionInfo info);
	Result updateInfo(PositionInfo info);
	Result deleteInfo(PositionInfo info);
	Result getInfoLow();

	Result getifdamagedcardata(String theinputdata);
	Result getDeviceNumber(PqiaData info);
	Result getInfoAsFrameNum1(PqiaData info);
	Result getInfoAsFrameNum2(PqiaData info);
	Result getInfoAsFrameNum3(PqiaData info);

	
	Result getInfoAsTime(PositionInfo info);
	
	
	PositionInfo getTimeAsMac(String mac);


}
