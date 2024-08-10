package com.app.dto;

//import javax.validation.constraints.Email;
//
//import javax.validation.constraints.NotEmpty;
//
//import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthRequest
{
//	@NotEmpty(message = "Email must be supplied")
//	@Email(message = "Invalid email format")
	private String email;

//	@NotEmpty(message = "Password must be supplied")
	private String pwd;
}
