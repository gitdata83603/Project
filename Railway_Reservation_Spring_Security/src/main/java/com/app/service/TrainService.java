package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.OneTrainRespDto;
import com.app.dto.TrainReqDto;
import com.app.dto.TrainRespDto;
import com.app.dto.TravelDateBookingRespDto;
import com.app.dto.UpdateTrainNoNameSeatsDTO;
import com.app.dto.UpdatetrainTimeDTO;
import com.app.entities.Train;

public interface TrainService 
{
	ApiResponse addNewTrain(TrainReqDto dto);

//	List<Train> getTrainDetails(String source, String destination);
	List<TrainRespDto> getTrainDetails(String source, String destination,LocalDate traveldate);

	List<TrainRespDto> getAllTrainDetails();

	OneTrainRespDto getOneTrainDetail(Long trainId);

	OneTrainRespDto getTrainDetailsByNumber(String trainNumber);

	List<TravelDateBookingRespDto> getTrainTraveldateDetailsByNumber(String trainNumber);

	ApiResponse updateTrainTravelTimemethod(UpdatetrainTimeDTO updatetrainTimeDTO, String trainNumber);

	ApiResponse updateTrainNoNameSeatService(UpdateTrainNoNameSeatsDTO updatetrainDTO, String trainNumber);

	ApiResponse getOneTrainDetailsByNumber(String trainNumber);
}
