package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "stations")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Station extends BaseEntity
{
  
   @Column(name = "station_code",unique = true)
   private String code;
   @Column(name="station_name")
   private String name;
	
}
