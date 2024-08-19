package com.app.dto;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//DTO : data transfer object (used to exchange the data between 
//REST  client n REST server)
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse 
{
	private Long id;
	private String number;
	private String message;
	private LocalDateTime timeStamp;

	public ApiResponse(String message) {
		super();
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
	public ApiResponse(Long id,String number,String message)
	{
		this.id=id;
		this.number=number;
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
	public ApiResponse(String number,String message)
	{
		this.number=number;
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
}
