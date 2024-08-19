package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "train_date_schedule")
@Getter
@Setter
@NoArgsConstructor
public class TravelDate extends BaseEntity 
{
	
   @Column(name = "journey_start_date")	
   private LocalDate startDate;
   @Column(name = "journey_end_date")	
   private LocalDate endDate;
   /*
    private Integer totalseats;
    private LocalDate endDate;
    */
   
   @Column(name = "total_seat_capacity")	
   private Integer totalseats;
   
   private Integer totalEconomyClassSeats;//**added
   private Integer totalFirstClassSeats;//**added
   //TOTAL economy class seats
   //total first class seats
   
   
   @Column(name = "total_booked_seats")	
   private Integer bookedSeats;
   
   private Integer totalEconomyClassBookedSeats;//**added
   private Integer totalFirstClassBookedSeats;//**added
   //TOTAL economy class booked seats
   //total first class booked seats
   
   
   @ManyToOne                         //unidirectional with train
   private Train train;
}
/*
	private Integer travelDateId;//
	private Integer trainid;//
	private String number; //
	private LocalDate startDate;
	private Integer bookedSeats;
	private Integer totalEconomyClassBookedSeats;//**added
    private Integer totalFirstClassBookedSeats;//**added
    private Integer totalEconomyClassSeats;//**added
    private Integer totalFirstClassSeats;//**added
*/