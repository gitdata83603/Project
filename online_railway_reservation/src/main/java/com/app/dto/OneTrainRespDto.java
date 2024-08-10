package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.app.entities.TravelDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OneTrainRespDto
{
	private Long id;  //from train
    private String number;//from train
	private String name;//from train
	private LocalDate date;//start_date-->from train
	private String SourceStationName;
	private LocalTime sourcearrival;//use getter in Route to set it     -->from Route
	private String destinationStationName;
	private LocalTime destinatiionarrival;//use getter in Route to set it   -->from Route
	private Integer totalseats;//from train
	private Integer distance;////updated between stations    //distance of destination from train start station - distance of source from  train start station
    private Double fare;
    private List<TravelDateRespDto> dates;
    private List<RouteRespDto> routes;
}
