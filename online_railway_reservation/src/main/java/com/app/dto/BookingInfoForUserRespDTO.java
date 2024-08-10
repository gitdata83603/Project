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
public class BookingInfoForUserRespDTO 
{
	   private Long id;
	   private String fromStation;
	   private String toStation;
	   private LocalDate startDate;
	   private LocalDate endDate;
	   private Integer totalSeatsBooked;
	   private String allocatedSeatNuumbers;
	   private Integer ticketprice;
	   private Integer distance;
	   private String passangerInfo;
	   private LocalTime sourceArrival;
	   private LocalTime sourceDeparture;
	   private LocalTime arrivalAtDestination;
	   private Integer cancellationStatus;
	   private String cancelled;
}
