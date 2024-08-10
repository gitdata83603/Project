package com.app.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CancellationRespDTO 
{
	private String reasonForCancellation;  
    private Double amount;  
	private LocalDate dateOfcancellation;
}
