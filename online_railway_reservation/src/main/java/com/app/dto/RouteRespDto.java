package com.app.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RouteRespDto 
{
	 private Integer distanceFromStart;
     private LocalTime arrivalTime;  //parse  to LocalTime
     private LocalTime departureTime;
     //private Long train_id;
     private String stationName;
}
