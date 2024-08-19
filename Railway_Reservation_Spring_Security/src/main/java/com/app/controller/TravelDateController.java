package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.TrainReqDto;
import com.app.dto.TravelDateReqDto;
import com.app.service.TravelDateService;

@CrossOrigin
@RestController
@RequestMapping("/set/train/date")
public class TravelDateController 
{
  
	@Autowired
	TravelDateService traveldateservice;
	
	public TravelDateController() {
		// TODO Auto-generated constructor stub
	}
	
	
	/*    
	 * Description - Add new Date
	 *  URL - http://host:8080/set/train/date
	 * Method - POST 
	 * payload -   
             {
			  "startDate": "2024-07-28",
			  "endDate": "2024-07-28",
			  "trainid": 1
			 }
	 * Successful response - SC 201 + mesg (ApiResponse)
	 * Error response - SC 400 , error mesg -wrapped in DTO(ApiResponse)  
	 */
	@PostMapping("/add")
	public ResponseEntity<?> addDateToTrain(@RequestBody TravelDateReqDto dto) 
	{
		System.out.println("in add date"+dto);
		try
		{
		   return ResponseEntity.status(HttpStatus.CREATED).body(traveldateservice.addNewDateToTrain(dto));
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
					
		}
	}
	
}
