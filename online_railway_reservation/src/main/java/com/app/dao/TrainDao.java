package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Train;
import java.lang.String;
import java.util.List;
import java.util.Optional;

public interface TrainDao extends JpaRepository<Train, Long>
{
   Optional<Train> findByNumber(String number);//to check unique train number
   
}
