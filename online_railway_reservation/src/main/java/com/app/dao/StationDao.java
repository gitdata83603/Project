package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Station;
import java.lang.String;
import java.util.List;
import java.util.Optional;

public interface StationDao extends JpaRepository<Station, Long>
{
   Optional<Station> findByCode(String code);
   List<Station> findByName(String name);//*********
}
