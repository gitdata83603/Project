package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.PricePerKm;

public interface PriceDao extends JpaRepository<PricePerKm, Long> 
{
   
}
