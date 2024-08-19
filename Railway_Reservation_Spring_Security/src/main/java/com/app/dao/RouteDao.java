package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Route;
import com.app.entities.Train;

public interface RouteDao extends JpaRepository<Route, Long>
{
	
   @Query("select r from Route r where r.station.name=:Source or r.station.name=:destination")
   List<Route> findroutes(String Source,String destination);

   
   @Query("select r.train from Route r where r.train.id=:id")
    List<Train> findTrains(Long id);
   
   List<Route> findByTrainId(Long id);

}
