package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.StationReqDTO;
import com.app.dto.StationResponseDTO;
import com.app.dto.UpdateStationReqDto;

public interface StationService
{

	ApiResponse addNewStation(StationReqDTO dto);

	ApiResponse updateStation(UpdateStationReqDto updateStationReqDto);

	List<StationResponseDTO> getAllStationDetails();
   
	
}
