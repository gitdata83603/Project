package com.app.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Booking;

public interface BookingDao extends JpaRepository<Booking, Long>
{
   List<Booking> findByTrainDateId(Long Id);
   List<Booking> findByUserId(Long Id);   
}
