package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRespDTO 
{
	private String status;
	private String Role;
	private Long id;
	private String firstName;
	private String email;
}
