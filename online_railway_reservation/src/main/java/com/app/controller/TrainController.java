package com.app.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
import com.app.dto.TrainReqDto;
import com.app.dto.UpdateTrainNoNameSeatsDTO;
import com.app.dto.UpdatetrainTimeDTO;
import com.app.dto.updatetimedto;
import com.app.service.TrainService;

@CrossOrigin
@RestController
@RequestMapping("/train")
public class TrainController 
{

	
	@Autowired
	TrainService trainService;
	
	public TrainController() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	/*    
	 * Description - Add new Train
	 *  URL - http://host:8080/train/add
	 * Method - POST 
	 * payload -   
 {
  "number": "IND9941",
  "name": "KARAD EXPRESS",
  "startstationid": 1, //dropdown
  "endstationid": 3,   //dropdown
  "date": "2024-07-25",   **********
  "arrival": "10:15:30",
  "departure": "11:55:20",
  "totalseats": 500,
  "bookedseats": 0,   ************
  "distance": 100,
  "enddate": "2024-07-25",***********
  "destarrival": "13:55:20"
}
	 * Successful response - SC 201 + mesg (ApiResponse)
	 * Error response - SC 400 , error mesg -wrapped in DTO(ApiResponse)  
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> addTrain(@RequestBody TrainReqDto dto) 
	{
		System.out.println("in add tarin"+dto);
		try
		{
		   return ResponseEntity.status(HttpStatus.CREATED).body(trainService.addNewTrain(dto));
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
					
		}
	}
	
	
	
	
	
	
	/*
	 * Desc - Get tarin details By Source,destination,traveldate URL - http://host:8080/train/{source}/{destination}/{traveldate}
	 *                                                               /SANGLI/TAKARI/2024-07-08 
	 *                                                         [
																  {
																    "id": 1,
																    "tavelDateId": 4,
																    "destinationRouteid": 2,
																    "sourceRouteid": 1
																    
																    "number": "INDMH9941",
																    "name": "KADEGAON EXPRESS",
																    "date": "2024-07-11",
																    "source": "sanglee",
																    "sourcearrival": "10:15:00",
																    
																    "destination": "kadegaon",
																    "destinatiionarrival": "14:50:00",
																    
																    "totalseats": 400,
																    "dist": 200,
																    "fare": null,
																    
																    
																  }
																]
	 * Method - GET 
	 * payload - NONE 
	 * Successful Resp - SC 200 
	 *  Error resp - SC404 , 
	 *  error mesg -wrapped in ApiResponse DTO
	 */
	@GetMapping("/{source}/{destination}/{traveldate}")
	public ResponseEntity<?> gettrain(@PathVariable String source,@PathVariable String destination,@PathVariable String traveldate ) 
	{																										//////////////////////
		System.out.println("in get train " + source+"  "+destination);
		try
		{
			
			return ResponseEntity.ok(trainService.getTrainDetails(source,destination,LocalDate.parse(traveldate)));
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	
	
	
	/*
	 * Desc - Get all train details URL - http://host:8080/train/
	 * Method - GET 
	 * payload - NONE 
	 * Successful Resp - SC 200 
	 *  Error resp - SC404 , 
	 *  error mesg -wrapped in ApiResponse DTO
	 */	
	
	@GetMapping("/traininfo")
	public ResponseEntity<?> getAlltrain() 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(trainService.getAllTrainDetails());
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	


	
	
	/*
	 * Desc - Get one train details URL - http://host:8080/train/{trainId}
	 * Method - GET 
	 * payload - NONE 
	 * Successful Resp - SC 200 
	 *  Error resp - SC404 , 
	 *  error mesg -wrapped in ApiResponse DTO
	 */	
	
	@GetMapping("{trainId}")
	public ResponseEntity<?> getOneTrainDetails(@PathVariable Long trainId) 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(trainService.getOneTrainDetail(trainId));
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	
	/*
	 * Desc - Get  train details by Train Number URL - http://host:8080/train/number/{trainNumber}
	 * Method - GET 
	 * payload - NONE 
	 * Successful Resp - SC 200 
	 *  Error resp - SC404 , 
	 *  error mesg -wrapped in ApiResponse DTO
	 */	
	
	@GetMapping("/number/{trainNumber}")
	public ResponseEntity<?> getTrainDetailsByTrainNumber(@PathVariable String trainNumber) 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(trainService.getTrainDetailsByNumber(trainNumber));
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	@GetMapping("/onenumber/abc/{trainNumber}")
	public ResponseEntity<?> getOneTrainDetailsByTrainNumber(@PathVariable String trainNumber) 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(trainService.getOneTrainDetailsByNumber(trainNumber));
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	/*
	 * Desc - Get  train traveldate details by Train Number URL - http://host:8080/train/traveldate/{trainNumber}
	 * Method - GET 
	 * payload - NONE 
	 * Successful Resp - SC 200 
	 *  Error resp - SC404 , 
	 *  error mesg -wrapped in ApiResponse DTO
	 */	
	@GetMapping("/traveldate/{trainNumber}")
	public ResponseEntity<?> getTrainTraveldateDetailsByTrainNumber(@PathVariable String trainNumber) 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(trainService.getTrainTraveldateDetailsByNumber(trainNumber));
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	/*
	 	    
	    B.update travel time --> requires train id , route id , all bookings related to given train 
	      two cases-->
	      
	      	  A.already has booked(i.e. changing in traveltime of whole train)
	      	    --> requires changes in train,route,booking table
	      	    
	      	  B.No bookings
	            --> doesn't require changes in booking table but only train and route table
	      
	      steps-->
	      1.find train id by given train number
	      2.find the difference between the earlier arrival and new arrival + difference between earlier arrival and departure
	      3.update source arrival,departure and destination arrival in train class + route class for both source and destination
	      4.if(earlier arrival < new arrival)
	        A.add the difference time in each route arrival
	        B.add the difference between earlier arrival and departure in departure of every route
	      5.if(earlier arrival > new arrival)
	        A.substract the difference time in each route arrival
	        B.add the difference between earlier arrival and departure in departure of every route
	      
	 
	 
	 
Update Time--> 
    ////////////
    classes involved
     1.Train for source and destination arrival time
     2.Route
     3.booking
   ////////////   
	class Train
	{
	 	1.private LocalTime arrival;     //source arrival
	 	2.private LocalTime destarrival; //destination arrival
	 	3.private LocalTime departure;   //source departure
	  	4.private Integer totalseats;
	}
	
	
	 **Requires updation in Route
	   
	   class Route
	   {
	     private LocalTime arrival;
         private LocalTime departure;   
	   }
	   /train/
	 */
	@PutMapping("updatetime/{trainNumber}") //updatetimedto
	public ResponseEntity<?> updateTrainTravelTime(@RequestBody updatetimedto updatetrainTimeDTO ,@PathVariable String trainNumber) 
	{																										
		System.out.println("HI 1");
		UpdatetrainTimeDTO updatetrainTimeDTO1=new UpdatetrainTimeDTO();
		updatetrainTimeDTO1.setSourcearrivaltime(LocalTime.parse(updatetrainTimeDTO.getArrival()));
		//updatetrainTimeDTO1.setDestinationarrivaltime(LocalTime.parse(updatetrainTimeDTO.getDestarrival()));
		System.out.println("HI 2");
		try
		{
			
			return ResponseEntity.status(HttpStatus.CREATED).body(trainService.updateTrainTravelTimemethod(updatetrainTimeDTO1,trainNumber));
		} //status(HttpStatus.CREATED).body(trainService.addNewTrain(dto))
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	
	/*
	 
	  class UpdateTrainNoNameSeatsDTO
	  {
     	private String number;
     	private String name;
     	private Integer totalseats;
      }
     
     URL -->  http://host:8080/train/updatetrain/{trainNumber}
     
     payload
	    {
		  "number": "MH1020",
		  "name": "palus",
		  "totalseats": 200,
		  "totalEconomyClassSeats": 115,
		  "totalFirstClassSeats": 85
		}
     
	 */
	
	
	@PutMapping("updatetrain/set/{trainNumber}") //updatetimedto
	public ResponseEntity<?> updateTrainNoNameSeats(@RequestBody UpdateTrainNoNameSeatsDTO updatetrainDTO ,@PathVariable String trainNumber) 
	{																										
		
		try
		{
			
			return ResponseEntity.status(HttpStatus.CREATED).body(trainService.updateTrainNoNameSeatService(updatetrainDTO,trainNumber));
		} //status(HttpStatus.CREATED).body(trainService.addNewTrain(dto))
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
}
