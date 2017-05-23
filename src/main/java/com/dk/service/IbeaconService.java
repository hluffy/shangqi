package com.dk.service;

import com.dk.object.IbeaconInfo;
import com.dk.result.Result;

public interface IbeaconService {
	Result getInfos();
	Result updateInfo(IbeaconInfo info);
	Result getInfo(IbeaconInfo info);
	Result addInfo(IbeaconInfo info);
	Result deleteInfo(IbeaconInfo info);

}
