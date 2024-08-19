package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.TravelDateReqDto;

public interface TravelDateService
{

	ApiResponse addNewDateToTrain(TravelDateReqDto dto);

}
