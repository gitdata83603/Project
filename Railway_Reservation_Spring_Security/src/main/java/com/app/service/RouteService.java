package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.RouteReqDto;

public interface RouteService 
{
	ApiResponse addNewRoute(RouteReqDto dto);

}
