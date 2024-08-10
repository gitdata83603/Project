package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Cancellation;

public interface CancelBooking extends JpaRepository<Cancellation, Long> 
{
    Optional<Cancellation> findByBookingId(Long Id);
}
