package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CancellationPolicy;

public interface CancelPolicy extends JpaRepository<CancellationPolicy, Long> 
{

	
}
