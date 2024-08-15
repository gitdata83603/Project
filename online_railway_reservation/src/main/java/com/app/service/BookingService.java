package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.BookingInfoForAdminDTO;
import com.app.dto.BookingInfoForUserRespDTO;
import com.app.dto.BookingReqDto;
import com.app.dto.CancellationReqDto;
import com.app.dto.CancellationRespDTO;
import com.app.entities.Cancellation;

public interface BookingService 
{

	ApiResponse addNewBooking(BookingReqDto dto);

	ApiResponse cancelBooking(CancellationReqDto dto);

	List<BookingInfoForAdminDTO> getBookingdetailsForAdmin(Long traveldateid);

	CancellationRespDTO getCancellationDetailsForAdmin(Long bookingid);

	List<BookingInfoForUserRespDTO> getBookingdetailsForUser(Long userid);

}
