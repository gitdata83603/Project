package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CancellationReqDto 
{
	   private Long booking_id;
	   private String reasonForCancellation;
}
