package com.app.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.dto.RegisterDto;
import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.security.JwtUtils;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserSignInSignupController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authMgr;
	
	/*
	 * URL - http://host:port/users/signin Method - POST request payload : Auth req
	 * DTO : email n password resp payload : In case of success : Auth Resp DTO :
	 * mesg + JWT token + SC 201 In case of failure : SC 401
	 * 
	 */

	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody  SigninRequest request)
	{
		System.out.println("in sign in" + request);
		//create a token to store un verified user email n pwd
		UsernamePasswordAuthenticationToken token=new 
				UsernamePasswordAuthenticationToken(request.getEmail(), 
						request.getPassword());
	
		System.out.println(token+"*************************************************");
		//invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
  		
		System.out.println(verifiedToken+"*************************************************");
		//=> auth successful !
		System.out.println(verifiedToken.getPrincipal().getClass());//custom user details object
		
		//create JWT n send it to the clnt in response
		SigninResponse resp=new SigninResponse
				(jwtUtils.generateJwtToken(verifiedToken),
				"Successful Auth!!!!");
		return ResponseEntity.
				status(HttpStatus.CREATED).body(resp);
	}
	
	/*
	 	@PostMapping("/signin") //@RequestMapping(method=POST)
	public ResponseEntity<?> signInUser(@RequestBody @Valid AuthRequest request)
	{
		
		System.out.println("in signin " + request);
		
			return ResponseEntity.ok(
					userService.authenticateUser(request));
		
	}
	 */
	
	@PostMapping("/register") //@RequestMapping(method=POST)
	public ResponseEntity<?> registerUser(@RequestBody RegisterDto request)
	{
		
		System.out.println("in signin " + request);
		
			return ResponseEntity.ok(
					userService.addUser(request));
		
	}

}






  
 
