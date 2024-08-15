package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.PriceReqDTO;
import com.app.entities.PricePerKm;

public interface PriceService 
{

	ApiResponse setPricePerKm(PriceReqDTO dto);

	ApiResponse updatePricePerKm(PriceReqDTO dto);

	PricePerKm getpriceperkm();
    
}
