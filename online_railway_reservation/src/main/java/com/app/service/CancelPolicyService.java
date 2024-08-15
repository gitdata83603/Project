package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.CancelPolicyReqDTO;

public interface CancelPolicyService 
{

	ApiResponse setdays(CancelPolicyReqDTO dto);

}
