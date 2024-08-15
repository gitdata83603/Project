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
import com.app.dto.CancelPolicyReqDTO;
import com.app.dto.PriceReqDTO;
import com.app.service.CancelPolicyService;
@CrossOrigin
@RestController
@RequestMapping("/cancellationpolicy")
public class CancelPolicyController 
{
	
	@Autowired
	CancelPolicyService cancelPolicyService;
 
	public CancelPolicyController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping
	public ResponseEntity<?> setDays(@RequestBody CancelPolicyReqDTO dto) 
	{
	
		try
		{
		   return ResponseEntity.status(HttpStatus.CREATED).body(cancelPolicyService.setdays(dto));
		}
		catch (RuntimeException e)
		{
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
					
		}
	}
}
