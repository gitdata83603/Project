package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RouteReqDto 
{

	 /*
    private Integer distance;
       private LocalTime arrival;
        private LocalTime departure;
          private Train train;
            private Station station;
          
    */
	
	 private Integer distance;
     private String arrival;  //parse  to LocalTime
     private String departure;
     private Long train_id;
     private Long station_id;
}
