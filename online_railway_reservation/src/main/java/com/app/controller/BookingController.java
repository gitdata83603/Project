package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.BookingReqDto;
import com.app.dto.CancellationReqDto;
import com.app.dto.TrainReqDto;
import com.app.service.BookingService;
@CrossOrigin
@RestController
@RequestMapping("/booking")
public class BookingController 
{

	@Autowired
	BookingService bookingService;
	
	public BookingController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/*
	 url--> http://localhost:8080/booking/add
	 method--> POST
	 
	 
	 payload-->
			    {  
			      "trainId": 0,
				  "userId": 0,
				  "trainScheduleId": 0,
				  "destinationRouteid": 0,
				  
				  
				  "totalFirstSeats": 0,
				  "totalEconomySeats": 0,
				  "passangerInfo": "string",
				  "sourceRouteid": 0
				}
	 */
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addBooking(@RequestBody BookingReqDto dto) 
	{
		
		try
		{
		   return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addNewBooking(dto));
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
					
		}
	}
	
	
	
	
	/*
	 url--> http://localhost:8080/booking/cancel
	 method--> POST
	 
	 
	 payload-->
			    {  

				  "booking_id" : 0,
	              "reasonForCancellation":"string"
				}
	 */
	
	@PostMapping("/cancel")
	public ResponseEntity<?> cancelBooking(@RequestBody CancellationReqDto dto) 
	{
		
		try
		{
		   return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.cancelBooking(dto));
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
					
		}
	}
	
	
	
	
	
	/*
	 * Desc - Get booking details for admin URL - http://host:8080/booking/{traveldateid}
	 * Method - GET 
	 * payload - NONE 
	 * Successful Resp - SC 200 
	 *  Error resp - SC404 , 
	 *  error mesg -wrapped in ApiResponse DTO
	 */	
	
	@GetMapping("{traveldateid}")
	public ResponseEntity<?> bookingdetailsForAdmin(@PathVariable Long traveldateid) 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(bookingService.getBookingdetailsForAdmin(traveldateid));
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	/*
	 * Desc - Get booking details for User URL - http://host:8080/booking/user/{userid}
	 * Method - GET 
	 * payload - NONE 
	 * Successful Resp - SC 200 
	 *  Error resp - SC404 , 
	 *  error mesg -wrapped in ApiResponse DTO
	 */	
	@GetMapping("/user/{userid}")
	public ResponseEntity<?> bookingdetailsForUser(@PathVariable Long userid) 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(bookingService.getBookingdetailsForUser(userid));
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	
	/*GET CANCELLATIONS DETAILS
	  
	 * Desc - Get booking details for admin URL - http://host:8080/booking/{bookingid}
	 * Method - GET 
	 * payload - NONE 
	 * Successful Resp - SC 200 
	 *  Error resp - SC404 , 
	 *  error mesg -wrapped in ApiResponse DTO
	 */	
	@GetMapping("/cancellation/{bookingid}")
	public ResponseEntity<?> getCancellationDetails(@PathVariable Long bookingid) 
	{																										
		
		try
		{
			
			return ResponseEntity.ok(bookingService.getCancellationDetailsForAdmin(bookingid));
		} 
		catch (RuntimeException e) 
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
    	
	
	
}
