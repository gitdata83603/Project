package com.app.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "trains")
@Getter
@Setter
@NoArgsConstructor

public class Train extends BaseEntity
{
    @Column(name = "train_no",unique = true)  
	private String number;
	
    @Column(name = "train_name")
	private String name;
	
    @OneToOne          //*************************************** unidirectional train has start_station_id(one to one)
    @JoinColumn(name = "start_station_id",nullable = false)
	private  Station start;
	
    @OneToOne         //*************************************** unidirectional train has end_station_id(one to one)
    @JoinColumn(name = "destition_station_id",nullable = false)
	private  Station end;
	
    				 //***************************************  Bidirectional one train have many routes 
  
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "train")
    private List<Route> routes=new ArrayList<Route>();
    
//    @Column(name = "travel_date")														U
//	private LocalDate date;//rrrrrrrrrrrrrrrrrrrrrrr
	
//    @Column(name = "travel_end_date")													U
// 	private LocalDate enddate;//rrrrrrrrrrrrrrrrrrrrrrr
    
    @Column(name = "arrival_time")
	private LocalTime arrival;
	
    @Column(name = "journey_end_time")
	private LocalTime destarrival;//***************
    
    @Column(name = "departure_time")
	private LocalTime departure;
	
    @Column(name = "seat_capacity")
	private Integer totalseats;

//*****************************************************************added    
    private Integer totalEconomyClassSeats;//**added
    private Integer totalFirstClassSeats;//**added
	//TOTAL economy class seats
    //total first class seats
    /*
     private String number;
     private String name;
     private Integer totalseats;
     
     */
    
//    @Column(name = "booked_seats")												    U
//	private Integer bookedseats;//rrrrrrrrrrrrrrrrrrrrrrr

    @Column(name="total_distance")
    private Integer distance;
    
	@Override
	public String toString() {
		return "Train [number=" + number + ", name=" + name + ", date="  + ", arrival=" + arrival + ", departure="
				+ departure + ", totalseats=" + totalseats + ", bookedseats=" +  "]";
	}
	
	public void addTrainRoute(Route route)
	{
	   this.routes.add(route);
	   route.setTrain(this);
		
	}
   
	
	/*
	 
	 **update train-->
	
	 UI-->
	 -->two options
	 
	    A.update travel date  --> 
	     
	     
	     payload -->
	     
	     1)
			     {
			       trainnumber:
			     }  //find the train
	     2)
			     {
			       traveldateid: 
			     }  
			      
	      steps-->
	            1.enter train number 
	            2.find train id
	            3.show all trains traveldate related to that id
	            4.user will select particular travel date
	            5.get traveldate id --> update travel date  of train in traveldate table --> update all bookings whose travel date id matches the given traveldate id
	            
	    requires train id, travel date id, bookings related to given traveldate id
	    
	    
	    
	    
	    B.update travel time --> requires train id , route id , all bookings related to given train 
	      two cases-->
	      
	      	  A.already has booked(i.e. changing in traveltime of whole train)
	      	    --> requires changes in train,route,booking table
	      	    
	      	  B.No bookings
	            --> doesn't require changes in booking table but only train and route table
	      
	      steps-->
	      1.find train id by given train number
	      2.find the difference between the earlier arrival and new arrival + difference between earlier arrival and departure
	      3.update source arrival,departure and destination arrival in train class + route class for both source and destination
	      4.if(earlier arrival < new arrival)
	        A.add the difference time in each route arrival
	        B.add the difference between earlier arrival and departure in departure of every route
	      5.if(earlier arrival > new arrival)
	        A.substract the difference time in each route arrival
	        B.add the difference between earlier arrival and departure in departure of every route
	      
	 
	 
	 
Update Time--> 
    ////////////
    classes involved
     1.Train for source and destination arrival time
     2.Route
     3.booking
   ////////////   
	class Train
	{
	 	1.private LocalTime arrival;     //source arrival
	 	2.private LocalTime destarrival; //destination arrival
	 	3.private LocalTime departure;   //source departure
	  	4.private Integer totalseats;
	}
	
	
	 **Requires updation in Route
	   
	   class Route
	   {
	     private LocalTime arrival;
         private LocalTime departure;   
	   }
	 
	 
	 
	 
Update Date--> 

      //////////////
      classes involved
        1.TravelDate
        2.Booking
      //////////////

	 **updation of traveldate of train-->
	 	  
      1. private Integer totalseats;
      2. private LocalDate endDate;
    
	 
	 **two cases-->
	      A.Booking(total booked seats = 0)
	      B.No Booking(total booked seats!=0)
	
	  
	 update date case -->   
	     A. will not be affected by any of above cases 
	     
	     
	 **Case Of No Booking
	  A. 
	   
	 
	 */
    
    
}
