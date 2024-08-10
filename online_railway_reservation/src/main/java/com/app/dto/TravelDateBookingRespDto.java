package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;	
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TravelDateBookingRespDto 
{
	private Long travelDateId;//
	private Long trainid;//
	private String number; //
	private LocalDate startDate;
	private Integer totalseats;
	private Integer bookedSeats;
	private Integer totalEconomyClassBookedSeats;//**added
    private Integer totalFirstClassBookedSeats;//**added
    private Integer totalEconomyClassSeats;//**added
    private Integer totalFirstClassSeats;//**added
}
	
