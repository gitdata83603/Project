package com.app.controller;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.RegisterDto;
import com.app.entities.Role;
import com.app.service.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	@Autowired //byType
	private UserService userService;

	public UserController() {
		System.out.println("in ctor " + getClass());
	}

	/*
	 * Desc - User signin 
	 * URL - http://host:port/users/signin 
	 * Method - POST 
	 * payload - dto (email n pwd) 
	 * Successful Resp - SC 200, user details - all (dto) 
	 * Error resp - SC 400 , error mesg -wrapped in DTO(ApiResponse)
	 * 
	 */
	@PostMapping("/signin") //@RequestMapping(method=POST)
	public ResponseEntity<?> signInUser(@RequestBody @Valid AuthRequest request)
	{
		
		System.out.println("in signin " + request);
		
			return ResponseEntity.ok(
					userService.authenticateUser(request));
		
	}
	
	/*
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String address;	

	private Role role;
	 */
	@PostMapping("/register") //@RequestMapping(method=POST)
	public ResponseEntity<?> registerUser(@RequestBody RegisterDto request)
	{
		
		System.out.println("in signin " + request);
		
			return ResponseEntity.ok(
					userService.addUser(request));
		
	}

}
