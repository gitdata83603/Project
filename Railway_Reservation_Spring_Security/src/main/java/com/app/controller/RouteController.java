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
import com.app.dto.RouteReqDto;
import com.app.dto.TrainReqDto;
import com.app.service.RouteService;

@CrossOrigin
@RestController
@RequestMapping("/route")
public class RouteController 
{
	@Autowired
	RouteService routeService;
	
	public RouteController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	/*
		* Description - Add new Route
	 *  URL - http://host:8080/route/add
	 * Method - POST 

	 * Successful response - SC 201 + mesg (ApiResponse)
	 * Error response - SC 400 , error mesg -wrapped in DTO(ApiResponse)  	
			payload-->
			    {
				  "distance": 50,
				  "arrival": "10:45",
				  "departure": "10:55",
				  "train_id": 1,
				  "station_id":4 
				}
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> addRoute(@RequestBody RouteReqDto dto) 
	{
		System.out.println("in add Route"+dto);
		try
		{
		   return ResponseEntity.status(HttpStatus.CREATED).body(routeService.addNewRoute(dto));
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
					
		}
	}

}
