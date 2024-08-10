package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TrainReqDto 
{
	private String number;
	private String name;
	
	private Long startstationid;
	private Long endstationid;
	
	private LocalDate date;//*****
	private String arrival;
	private String departure;
	private Integer totalseats;
	private Integer totalEconomyClassSeats;//**added
	private Integer totalFirstClassSeats;//**added
	   
	   
	private Integer bookedseats;//*****
	private Integer totalEconomyClassBookedSeats;//**added
	private Integer totalFirstClassBookedSeats;//**added
	
	
	 private Integer distance;
	 
	 private LocalDate enddate;//*******
	 private String destarrival;
	 
}
