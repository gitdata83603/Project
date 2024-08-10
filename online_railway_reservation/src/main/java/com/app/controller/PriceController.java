package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.PriceReqDTO;
import com.app.dto.StationReqDTO;
import com.app.service.PriceService;
@CrossOrigin
@RestController
@RequestMapping("/price")
public class PriceController
{
	@Autowired
	PriceService priceService;
	
	  public PriceController() {
		// TODO Auto-generated constructor stub
	  }
	
	  
	  /*
	 * Description - Add price
	 *  URL - http://host:8080/price
	 * Method - POST 
	 * payload
 		    {
			  "firstClassPrice": 0,
			  "economyClassPrice": 0
			}
	 * response --> success massage wrapped in ApiResponse
	 * */
	  
		@PostMapping
		public ResponseEntity<?> setPrice(@RequestBody PriceReqDTO dto) 
		{
		
			try
			{
			   return ResponseEntity.status(HttpStatus.CREATED).body(priceService.setPricePerKm(dto));
			}
			catch (RuntimeException e)
			{
				System.out.println(e);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
						
			}
		}
		
		
		  /*
		 * Description - update price
		 *  URL - http://host:8080/price
		 * Method - PUT
		 * payload
	 		    {
				  "firstClassPrice": 0,
				  "economyClassPrice": 0
				}
		 * response --> success massage wrapped in ApiResponse
		 * */
		
		
		@PutMapping
		public ResponseEntity<?> updatePrice(@RequestBody PriceReqDTO dto) 
		{
		
			try
			{
			   return ResponseEntity.ok(priceService.updatePricePerKm(dto));
			}
			catch (RuntimeException e)
			{
				System.out.println(e);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
						
			}
		}
	  
	  
  
}
