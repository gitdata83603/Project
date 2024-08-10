package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.AuthRequest;
import com.app.dto.UserRespDTO;

public interface UserService {
//user signin
	UserRespDTO authenticateUser(AuthRequest dto);
}
