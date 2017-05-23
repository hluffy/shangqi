package com.dk.service;

import com.dk.object.User;
import com.dk.result.Result;

public interface UserService {
	Result getUserInfos();
	Result updateUserInfo(User user);
	Result addUserInfo(User info);
	Result deleteUserInfo(User info);
	Result loginUserInfo(User info);
	Result getUserInfo(User info);
	User getUserAsUserName(String userName);

}
