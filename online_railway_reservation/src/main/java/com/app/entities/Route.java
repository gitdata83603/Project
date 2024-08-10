package com.app.entities;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
public class Route extends BaseEntity
{
	
   @Column(name = "distance_from_source")	
   private Integer distance;
   @Column(name = "arrival_time")
   private LocalTime arrival;
   @Column(name = "departure_time")
   private LocalTime departure;
   
   @ManyToOne   //////////////************************************************  Bidirectional many routes can have same train id 
   @JoinColumn(name = "train_id",nullable = false)
   private Train train;
   
   @ManyToOne   //*************************************************************Unidirectional many routes can have same station id 
   @JoinColumn(name = "station_id",nullable = false)
   private Station station;

	@Override
	public String toString() 
	{
		return "Route [distance=" + distance + ", arrival=" + arrival + ", departure=" + departure + "]";
	}

   /*
    private Integer distance;
       private LocalTime arrival;
        private LocalTime departure;
          private Train train;
            private Station station;
          
    */
   
   
}
