package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateTrainNoNameSeatsDTO
{
	
    private String number;
    private String name;
    private Integer totalseats;
    private Integer totalEconomyClassSeats;//**added
    private Integer totalFirstClassSeats;//**added
    
}
