package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.AuthRequest;
import com.app.dto.RegisterDto;
import com.app.dto.UserRespDTO;

public interface UserService {

	UserRespDTO authenticateUser(AuthRequest dto);

	ApiResponse addUser(RegisterDto request);
}
/*
public interface UserService {

	ApiResponse addUser(RegisterDto request);
	//add signup method
}

*/