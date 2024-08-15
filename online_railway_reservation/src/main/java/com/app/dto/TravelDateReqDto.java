package com.app.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import com.app.entities.Train;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TravelDateReqDto 
{
	
	   private String startDate;
	   private String endDate;
	   private Long trainid;
}
