package com.app.service;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.RouteDao;
import com.app.dao.StationDao;
import com.app.dao.TrainDao;
import com.app.dao.TravelDateDao;
import com.app.dto.ApiResponse;
import com.app.dto.TravelDateReqDto;
import com.app.entities.BaseEntity;
import com.app.entities.Train;
import com.app.entities.TravelDate;

@Service
@Transactional
public class TravelDateServiceImpl implements TravelDateService 
{

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	TrainDao traindao;
	
	@Autowired
	TravelDateDao travelDateDao;
	
	public TravelDateServiceImpl() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ApiResponse addNewDateToTrain(TravelDateReqDto dto) 
	{
		// TODO Auto-generated method stub
	     Train train=traindao.findById(dto.getTrainid()).orElseThrow(()->new ResourceNotFoundException("train does not exists"));
	     TravelDate traveldatepersistent=travelDateDao.findByTrainId(train.getId()).get(0);
		/*
		   public class TravelDate extends BaseEntity 
			{
				
			   @Column(name = "journey_start_date")	
			   private LocalDate startDate;
			   @Column(name = "journey_end_date")	
			   private LocalDate endDate;
			   @Column(name = "total_seat_capacity")	
			   private Integer totalseats;
			   @Column(name = "total_booked_seats")	
			   private Integer bookedSeats;
			   
			   @ManyToOne                         //unidirectional with train
			   private Train train;
			}
		 */
	     
	     TravelDate travelDate=mapper.map(dto, TravelDate.class);
	     travelDate.setStartDate(LocalDate.parse(dto.getStartDate()));
	     travelDate.setEndDate(LocalDate.parse(dto.getEndDate()));
	     travelDate.setTotalseats(train.getTotalseats());
	     //travelDate.setTotalEconomyClassSeats(traveldatepersistent.getTotalEconomyClassSeats());
	     travelDate.setTotalEconomyClassSeats(train.getTotalEconomyClassSeats());
	     //travelDate.setTotalFirstClassSeats(traveldatepersistent.getTotalFirstClassSeats());
	     travelDate.setTotalFirstClassSeats(train.getTotalFirstClassSeats());
	     travelDate.setTotalEconomyClassBookedSeats(0);
	     travelDate.setTotalFirstClassBookedSeats(0);
	     travelDate.setBookedSeats(0);
	     travelDate.setTrain(train);
	     
	     TravelDate persistenttravelDate=travelDateDao.save(travelDate);
		return new ApiResponse("successfully added the new date for train operation"+persistenttravelDate.getId());
	}

}
