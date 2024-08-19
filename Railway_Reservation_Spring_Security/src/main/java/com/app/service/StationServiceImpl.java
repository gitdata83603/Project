package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.StationDao;
import com.app.dto.ApiResponse;
import com.app.dto.StationReqDTO;
import com.app.dto.StationResponseDTO;
import com.app.dto.UpdateStationReqDto;
import com.app.entities.Station;

@Service
@Transactional
public class StationServiceImpl implements StationService 
{

	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	StationDao stationdao;
	
	
	public StationServiceImpl() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ApiResponse addNewStation(StationReqDTO dto) 
	{
		// TODO Auto-generated method stub
		
		String msg="Failed to add the station, Please Enter a Unique Station Code";
		
		if(stationdao.findByCode(dto.getCode()).isEmpty())
		{
			Station station=mapper.map(dto, Station.class);
		    Station st= stationdao.save(station);	
		    msg="successfully added the station with id="+st.getId();
		}
		
		return new ApiResponse(msg);
	}

	@Override
	public ApiResponse updateStation(UpdateStationReqDto updateStationReqDto) 
	{
		// TODO Auto-generated method stub
	    Station station=stationdao.findById(updateStationReqDto.getId()).orElseThrow(()->new ResourceNotFoundException("Station Doesn't exists"));
		station.setName(updateStationReqDto.getName());
		station.setUpdatedOn(LocalDateTime.now());
		return new ApiResponse("Station Updated Successfully");
	}

	@Override
	public List<StationResponseDTO> getAllStationDetails() 
	{
		// TODO Auto-generated method stub
	    List<Station> stations=	stationdao.findAll();
		List<StationResponseDTO> dtos=new ArrayList<StationResponseDTO>();
		for(Station station : stations)
		{
			StationResponseDTO dto=new StationResponseDTO();
			dto.setId(station.getId());
			dto.setName(station.getName());
			dto.setCode(station.getCode());
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	

}
