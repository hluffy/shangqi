package com.dk.service;

import com.dk.object.LoraInfo;
import com.dk.result.Result;

public interface LoraService {
	Result getInfos();
	Result addInfo(LoraInfo info);
	Result updateInfo(LoraInfo info);
	Result deleteInfo(LoraInfo info);
	Result getInfo(LoraInfo info);

}
