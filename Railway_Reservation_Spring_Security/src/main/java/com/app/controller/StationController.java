package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.StationReqDTO;
import com.app.dto.UpdateStationReqDto;
import com.app.service.StationService;



@CrossOrigin
@RestController
@RequestMapping("/station")
public class StationController
{

	
	@Autowired
	StationService stationService;
	
	public StationController()
	{
		// TODO Auto-generated constructor stub
	System.out.println("inside station controller");
	
	}
	
	
	
	
	
	/*    
	 * Description - Add new station Post
	 *  URL - http://host:8080/station/add
	 * Method - POST 
	 * payload -    {
					  "code": "MH10KARAD",
					  "name": "KARAD"
					} 
	 * Successful response - SC 201 + mesg (ApiResponse)
	 * Error response - SC 400 , error mesg -wrapped in DTO(ApiResponse)  
	 */
	@PostMapping("/add")
	public ResponseEntity<?> addStation(@RequestBody StationReqDTO dto) 
	{
		System.out.println("in add station "+dto);
		try
		{
		   return ResponseEntity.status(HttpStatus.CREATED).body(stationService.addNewStation(dto));
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
					
		}
	}
	
	
	
	/*
	 * Desc - Update Station Details(station name) URL - http://host:8080/station/update
	 * Method - PUT
	 * payload-->
	        {
			  "id": 0,
			  "name": "string"
			}
	 * Successful Resp - SC 200 ,
	 *  Error resp - SC 404 , error mesg -wrapped in DTO
	 */
	@PutMapping("/update")
	public ResponseEntity<?> updateStation(@RequestBody UpdateStationReqDto updateStationReqDto)
	{
		
	
		try
		{
		   return ResponseEntity.ok(stationService.updateStation(updateStationReqDto));
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
					
		}
	}
	
	
	
	@GetMapping
	public ResponseEntity<?> getAllStations() 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(stationService.getAllStationDetails());
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	
	
}
