package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.PriceDao;
import com.app.dto.ApiResponse;

import com.app.dto.PriceReqDTO;
import com.app.entities.PricePerKm;

@Service
@Transactional
public class PriceServiceImpl implements PriceService
{
    
	@Autowired
	ModelMapper mapper;
	
	
	@Autowired
	PriceDao priceDao;
	
	@Override
	public ApiResponse setPricePerKm(PriceReqDTO dto)
	{
		// TODO Auto-generated method stub
		if(priceDao.findAll().isEmpty())
		{
			PricePerKm pricePerKm=mapper.map(dto, PricePerKm.class);	
			priceDao.save(pricePerKm);
			return new ApiResponse("Price inserted successfully");
		}
		return new ApiResponse("Prices are already exists in table , please update and not insert rhe prices");
		//return new PriceServiceImpl().updatePricePerKm(dto);
	}

	@Override
	public ApiResponse updatePricePerKm(PriceReqDTO dto) 
	{
		// TODO Auto-generated method stub
		PricePerKm pricePerKm=priceDao.findAll().get(0);
		mapper.map(dto, pricePerKm);
		
		return new ApiResponse("prices updated successfully");
	}

	
	@Override
	public PricePerKm getpriceperkm() {
		// TODO Auto-generated method stub
		
		PricePerKm price= priceDao.findAll().get(0);
		
		
		return price;
	}

}
