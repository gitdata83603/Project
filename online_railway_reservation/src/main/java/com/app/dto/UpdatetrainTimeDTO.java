package com.app.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdatetrainTimeDTO 
{

 	private LocalTime sourcearrivaltime;     
 	//private LocalTime destinationarrivaltime;
}
