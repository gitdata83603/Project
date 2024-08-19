package com.app.dto;

import java.time.LocalDateTime;

import com.app.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String address;	

	//private Role role;
}
