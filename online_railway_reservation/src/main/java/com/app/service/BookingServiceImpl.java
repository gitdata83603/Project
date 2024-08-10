package com.app.service;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ApiException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.BookingDao;
import com.app.dao.CancelBooking;
import com.app.dao.CancelPolicy;
import com.app.dao.PriceDao;
import com.app.dao.RouteDao;
import com.app.dao.TrainDao;
import com.app.dao.TravelDateDao;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.BookingInfoForAdminDTO;
import com.app.dto.BookingInfoForUserRespDTO;
import com.app.dto.BookingReqDto;
import com.app.dto.CancellationReqDto;
import com.app.dto.CancellationRespDTO;
import com.app.entities.Booking;
import com.app.entities.Cancellation;
import com.app.entities.PricePerKm;
import com.app.entities.Route;
import com.app.entities.Train;
import com.app.entities.TravelDate;
import com.app.entities.User;
//import com.app.helper.CancellationLogic;
import com.app.helper.CancellationLogic;

@Service
@Transactional
public class BookingServiceImpl implements BookingService 
{

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TrainDao trainDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	TravelDateDao travelDateDao; 
	
	@Autowired
	RouteDao routeDao;
	
	@Autowired
	PriceDao priceDao;
	
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	CancelBooking cancelBookingDao;
	
	@Autowired
	CancelPolicy  cancelPolicy;
	
	public BookingServiceImpl() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ApiResponse addNewBooking(BookingReqDto dto) 
	{
		// TODO Auto-generated method stub
		/*
		   	  private Long trainId;
			  private Long userId;
			  private Long trainScheduleId;
			  private Long SourceRouteid;
			  private Long destinationRouteid;
			  private Integer totalFirstSeats;
			  private Integer totalEconomySeats;
			  private String passangerInfo;
		 */
		Train train=trainDao.findById(dto.getTrainId()).orElseThrow(()->new ResourceNotFoundException("Train not found"));
		User user=userDao.findById(dto.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
		TravelDate travelDate=  travelDateDao.findById(dto.getTrainScheduleId()).orElseThrow(()->new ResourceNotFoundException("date not found"));
		Route sourceRoute= routeDao.findById(dto.getSourceRouteid()).orElseThrow(()->new ResourceNotFoundException("source route not found"));
		Route destinationRoute= routeDao.findById(dto.getDestinationRouteid()).orElseThrow(()->new ResourceNotFoundException("destination route not found"));
		
		
		
		//checking for seats availability
		
		if( dto.getTotalEconomySeats() > (travelDate.getTotalEconomyClassSeats()-travelDate.getTotalEconomyClassBookedSeats()))
		{
		   throw new ResourceNotFoundException("Economy class seats not available");	
		}
		
		if(dto.getTotalFirstSeats() > (travelDate.getTotalFirstClassSeats()-travelDate.getTotalFirstClassBookedSeats()))
		{
			 throw new ResourceNotFoundException("First class seats not available");	
		}
		
		
		
		
		/*
		 	//station   --> completed(sourceRoute,destinationRoute)
   @Column(name="from_station")
   private String fromStation;   
   @Column(name="to_station")
   private String toStation;
	
	
	*/
		Booking booking=new Booking();
		booking.setFromStation(sourceRoute.getStation().getName());
		booking.setToStation(destinationRoute.getStation().getName());
		
		
		/*
	
   //date --> completed(travelDate)
   @Column(name = "journey_start_date")	
   private LocalDate startDate;
   @Column(name = "journey_end_date")	
   private LocalDate endDate;
   */
   
		booking.setStartDate(travelDate.getStartDate());
		booking.setEndDate(travelDate.getEndDate());
		
   
   /*
   //time --> completed(sourceRoute,destinationRoute)
   @Column(name = "source_arrival_time")
   private LocalTime sourceArrival;
   @Column(name = "source_departure_time")
   private LocalTime sourceDeparture;
   @Column(name = "destination_arrival_time")
   private LocalTime arrivalAtDestination;
   */
		
		booking.setSourceArrival(sourceRoute.getArrival());
		booking.setSourceDeparture(sourceRoute.getDeparture());
		booking.setArrivalAtDestination(destinationRoute.getArrival());
		
		
		/*
   
   //seats  --> completed(BookingReqDto dto)  + update on traveldate 
   @Column(name = "total_seats_booked")	
   private Integer totalSeatsBooked;
   @Column(name = "seat_numbers")	
   private String allocatedSeatNuumbers;
      */
		
		booking.setTotalSeatsBooked(dto.getTotalEconomySeats()+dto.getTotalFirstSeats());//*******************
		booking.setEconomySeats(dto.getTotalEconomySeats());
		booking.setFirstSeats(dto.getTotalFirstSeats());
		String allocatedSeatNumbers=null;
		Integer totalEconomyClassSeats=dto.getTotalEconomySeats();
		Integer totalFirstClassSeats=dto.getTotalFirstSeats();
	    StringBuilder stringBuilder=new StringBuilder();
		
	    int i=1;
		while(totalEconomyClassSeats>0)
		{
		  
		  Integer seat=travelDate.getTotalEconomyClassBookedSeats()+ i++;
		  stringBuilder.append(seat+"E"+" , ");
		  totalEconomyClassSeats--;
		}
		 i=1;
		while(totalFirstClassSeats>0)
		{
		  
		  Integer seat=travelDate.getTotalFirstClassBookedSeats()+ i++;
		//  allocatedSeatNumbers=allocatedSeatNumbers+seat+"F"+" , ";
		  stringBuilder.append(seat+"F"+" , ");
		  totalFirstClassSeats--;
		}
		
		//update on traveldate 
		travelDate.setTotalEconomyClassBookedSeats(travelDate.getTotalEconomyClassBookedSeats()+dto.getTotalEconomySeats());
		travelDate.setTotalFirstClassBookedSeats(travelDate.getTotalFirstClassBookedSeats()+dto.getTotalFirstSeats());
		travelDate.setBookedSeats(travelDate.getBookedSeats()+dto.getTotalEconomySeats()+dto.getTotalFirstSeats());
		
		booking.setAllocatedSeatNuumbers(stringBuilder.toString());//*******************************seat numbers
		
		
		

		
		
		
		
		/*
   
   //distance((destinationRoute -sourceRoute)
   private Integer distance;
   */
		booking.setDistance(destinationRoute.getDistance()-sourceRoute.getDistance());
		
		
		
		
		/*
		   
		   //ticket price  (select on price)
		   private Integer ticketprice;
		   */
		
		   PricePerKm pricePerKm =priceDao.findAll().get(0);
		   booking.setTicketprice((dto.getTotalEconomySeats()*pricePerKm.getEconomyClassPrice()*booking.getDistance())+(dto.getTotalFirstSeats()*pricePerKm.getFirstClassPrice()*booking.getDistance()));
		
	
		   
		   
		   
		   /*
		 
   
   //passanger info  --> completed(BookingReqDto dto) 
   @Column(name = "passanger_info")	
   private String passangerInfo;
   
   */
		   booking.setPassangerInfo(dto.getPassangerInfo());
		   
		   
		   
		   
		   
		   
		   /*
   //Asssociation
   @ManyToOne
   @JoinColumn(name = "user_Id",nullable = false)
   private User user;
   
   @ManyToOne
   @JoinColumn(name = "train_schedule_Id",nullable = false)
   private TravelDate trainDate;
   
		 */
		   booking.setUser(user);
		   booking.setTrainDate(travelDate);
		   
		   booking.setCancellationStatus(0);
		   
		   bookingDao.save(booking);
		   
		  
		   
		return new ApiResponse("Booking successfull");
	}

	@Override
	public ApiResponse cancelBooking(CancellationReqDto dto) 
	{
	
		/*
	1.Find booking by booking_id -->persistent
 	2.Make cancellation_status in booking table as 1
 	3.update-->
	   
   		private Integer bookedSeats;
   		private Integer totalEconomyClassBookedSeats;//**added
   		private Integer totalFirstClassBookedSeats;//**added
 	4.create cancellation class object(setter to establish relationship between booking and cancellation + set reason for cancellations)
 	5.save() 
		 */
		
		Booking booking=bookingDao.findById(dto.getBooking_id()).orElseThrow(()-> new ResourceNotFoundException("Booking doesn't exist"));
		TravelDate travelDate=  travelDateDao.findById(booking.getTrainDate().getId()).orElseThrow(()-> new ResourceNotFoundException("Booking doesn't exist"));
		
		
		//can cancel the ticket only before 2 days(i.e. only upto before 2 days until start date)		
		//cancellation logic --> calling helper method given by a helper class
		
	     int days=cancelPolicy.findAll().get(0).getDays();
		 int result=CancellationLogic.ticketCancellation(LocalDate.now(), booking.getStartDate(),days);
		 if(result==0)
		 {
			throw new ApiException("cancellation of the tickets before one day is not allowed"); 
		 }
		
		
		
		travelDate.setBookedSeats(travelDate.getBookedSeats()-booking.getTotalSeatsBooked());
		travelDate.setTotalEconomyClassBookedSeats(travelDate.getTotalEconomyClassBookedSeats() - booking.getEconomySeats());	
		travelDate.setTotalFirstClassBookedSeats(travelDate.getTotalFirstClassBookedSeats()- booking.getFirstSeats());
		booking.setCancellationStatus(1);
		
		Cancellation cancellation=new Cancellation();
		cancellation.setAmount((double)booking.getTicketprice());
		cancellation.setBooking(booking);//*********************************association
		cancellation.setDateOfcancellation(LocalDate.now());
		cancellation.setReasonForCancellation(dto.getReasonForCancellation());
				
		cancelBookingDao.save(cancellation);
		return new ApiResponse("Ticket cancelled successfully! refund will be processed within 24 hours");
	}

	@Override
	public List<BookingInfoForAdminDTO> getBookingdetailsForAdmin(Long traveldateid) 
	{
		// TODO Auto-generated method stub
		List<Booking> bookings= bookingDao.findByTrainDateId(traveldateid);
		List<BookingInfoForAdminDTO> responses=new ArrayList<BookingInfoForAdminDTO>();
		
		for(Booking booking:bookings)
		{
			BookingInfoForAdminDTO dto=mapper.map(booking, BookingInfoForAdminDTO.class);
			dto.setId(booking.getId());
			if(booking.getCancellationStatus()==1)
			{
			  dto.setCancelled("YES");	
			}
			else
			{
				dto.setCancelled("NO");
				responses.add(dto);
			}
			
		}
		
		return responses;
	}

	@Override
	public CancellationRespDTO getCancellationDetailsForAdmin(Long bookingid) 
	{
		// TODO Auto-generated method stub
		
		Cancellation cancellation= cancelBookingDao.findByBookingId(bookingid).orElseThrow(()-> new ResourceNotFoundException("Cancellation for given Booking doesn't exist"));
		return mapper.map(cancellation, CancellationRespDTO.class);
	}

	@Override
	public List<BookingInfoForUserRespDTO> getBookingdetailsForUser(Long userid) 
	{
		// TODO Auto-generated method stub
	
		List<Booking> bookings= bookingDao.findByUserId(userid);
		for(Booking booking:bookings)
		{
		  System.out.println(booking.getFromStation()+"  "+booking.getToStation());	
		}
		
		List<BookingInfoForUserRespDTO> responses=new ArrayList<BookingInfoForUserRespDTO>();
		
		for(Booking booking:bookings)
		{
			BookingInfoForUserRespDTO dto=mapper.map(booking, BookingInfoForUserRespDTO.class);
		   dto.setId(booking.getId());
		   if(dto.getCancellationStatus()!=null &&  dto.getCancellationStatus()==1)
		   {
			dto.setCancelled("Yes");   
		   }
		   else
		   {
			dto.setCancelled("NO");   
		   }
		   responses.add(dto);
		}
		
		return responses;
	}

}
