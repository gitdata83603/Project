package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TrainRespDto 
{
   
	private Long id;  //from train
    private Long tavelDateId;
    private Long SourceRouteid;
    private Long destinationRouteid;
	private String number;//from train
	private String name;//from train
	private LocalDate date;//start_date-->from train
	 private String Source;
	private LocalTime sourcearrival;//use getter in Route to set it     -->from Route
	private String destination;
	private LocalTime destinatiionarrival;//use getter in Route to set it   -->from Route
	private Integer totalseats;//from train
	private Integer dist;////updated between stations    //distance of destination from train start station - distance of source from  train start station
    private Double fare;
//    private Integer totalEconomyClassBookedSeats;//**added
//    private Integer totalFirstClassBookedSeats;//**added
//    private Integer totalEconomyClassSeats;//**added
//    private Integer totalFirstClassSeats;//**added
    
}








/*

SOURCE -->SANLGI
DESTINATION-->TAKARI


OP-->
   
   [
		  {
		    "id": 1,
		    "createdOn": "2024-06-30",
		    "updatedOn": "2024-06-30T17:49:57.896214",
		    "number": "IND9941",
		    "name": "KARAD EXPRESS",
		    "start": {                       ///////////////STATION INFO
		      "id": 1,
		      "createdOn": "2024-06-30",
		      "updatedOn": "2024-06-30T17:31:52.303623",
		      "code": "MH10SANGLI",
		      "name": "SANGLI"
		    },
		    "end": {                        ///////////////STATION INFO
		      "id": 3,
		      "createdOn": "2024-06-30",
		      "updatedOn": "2024-06-30T17:32:40.345164",
		      "code": "MH09KOLHAPUR",
		      "name": "KOLHAPUR"
		    },
		    "date": "2024-07-25",
		    "enddate": "2024-07-25",
		    "arrival": "10:15:30",
		    "destarrival": "13:55:20",
		    "departure": "11:55:20",
		    "totalseats": 500,
		    "bookedseats": 0,
		    "distance": 100
		  }
   ]
*/