package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Train;
import com.app.entities.TravelDate;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TravelDateDao extends JpaRepository<TravelDate, Long> 
{
//        List<TravelDate> findByStartDate(LocalDate startdate);
	
	@Query("select d.train from TravelDate d where d.startDate=:startdate and d.train.id=:trainid")
	 Optional<Train>  findtrainwithdate(LocalDate startdate,Long trainid);

      List<TravelDate> findByTrainId(Long id);
      
      @Query("select t.id from TravelDate t where t.startDate=:startdate and t.train.id=:trainid")
      Long findTravelDateId(LocalDate startdate,Long trainid);
     
      

}
