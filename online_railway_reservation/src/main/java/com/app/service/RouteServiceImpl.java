package com.app.service;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.RouteDao;
import com.app.dao.StationDao;
import com.app.dao.TrainDao;
import com.app.dto.ApiResponse;
import com.app.dto.RouteReqDto;
import com.app.entities.Route;
import com.app.entities.Station;
import com.app.entities.Train;


@Service
@Transactional
public class RouteServiceImpl implements RouteService 
{
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	RouteDao routedao;
	
	@Autowired
	TrainDao traindao;
	
	@Autowired
	StationDao stationdao;

	public RouteServiceImpl() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public ApiResponse addNewRoute(RouteReqDto dto)
	{
		// TODO Auto-generated method stub
		
		/*
		  	 private Integer distance;
		     private String arrival;  //parse  to LocalTime
		     private String departure;
		     private Long train_id;
		     private Long station_id;
		 */
		Train train=traindao.findById(dto.getTrain_id()).orElseThrow(()->new ResourceNotFoundException("train does not exists"));//persistent
		Station station=stationdao.findById(dto.getStation_id()).orElseThrow(()->new ResourceNotFoundException("Station does not exists"));//persistent
	    
		Route route=mapper.map(dto, Route.class);//transient
		route.setArrival(LocalTime.parse(dto.getArrival()));
		route.setDeparture(LocalTime.parse(dto.getDeparture()));
		
		//establish relationship between Route and Station-->unidirectional
		route.setStation(station);
		
		//establish relationship between Route and Train
		train.addTrainRoute(route);   //changes in persistent entity --> will automatically fire insert query on route
		
		
		
		return new ApiResponse(train.getId(), train.getNumber(), "route added successfully"); 
				//ApiResponse("route added successfully");
	}

}
