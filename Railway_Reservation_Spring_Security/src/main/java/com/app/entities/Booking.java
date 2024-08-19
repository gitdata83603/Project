package com.app.entities;

import java.time.LocalDate;
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
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking extends BaseEntity
{
   
	//station
   @Column(name="from_station")
   private String fromStation;   
   @Column(name="to_station")
   private String toStation;
	
   //date
   @Column(name = "journey_start_date")	
   private LocalDate startDate;
   @Column(name = "journey_end_date")	
   private LocalDate endDate;
 
   /*
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
    */
   
   //time
   @Column(name = "source_arrival_time")
   private LocalTime sourceArrival;
   @Column(name = "source_departure_time")
   private LocalTime sourceDeparture;
   @Column(name = "destination_arrival_time")
   private LocalTime arrivalAtDestination;
   
   
   //seats
   @Column(name = "total_seats_booked")	
   private Integer totalSeatsBooked;
   @Column(name = "seat_numbers")	
   private String allocatedSeatNuumbers;
   private Integer economySeats;
   private Integer firstSeats;
   
   //ticket price
   private Integer ticketprice;
   
   
   //distance
   private Integer distance;
   
   
   //passanger info
   @Column(name = "passanger_info")	
   private String passangerInfo;
   
   
   //cancellation status 
   @Column(name = "cancellation_status")	
   private Integer cancellationStatus;
   
   //Asssociation
   @ManyToOne
   @JoinColumn(name = "user_Id",nullable = false)
   private User user;
   
   @ManyToOne
   @JoinColumn(name = "train_schedule_Id",nullable = false)
   private TravelDate trainDate;
   
   
   
   
}
