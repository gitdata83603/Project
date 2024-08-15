package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CancelPolicy;
import com.app.dto.ApiResponse;
import com.app.dto.CancelPolicyReqDTO;
import com.app.entities.CancellationPolicy;
@Service
@Transactional
public class CancelPolicyServiceImpl implements CancelPolicyService 
{

	
	@Autowired
	CancelPolicy cancelPolicy;
	
	@Override
	public ApiResponse setdays(CancelPolicyReqDTO dto)
	{
		// TODO Auto-generated method stub
	
		CancellationPolicy cp=new CancellationPolicy();
		cp.setDays(dto.getDays());
		cancelPolicy.save(cp);
		
		return new ApiResponse("added successfully");
	}

}
