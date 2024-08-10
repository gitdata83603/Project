package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookingReqDto 
{
  private Long trainId;
  private Long userId;
  private Long trainScheduleId;
  private Long SourceRouteid;
  private Long destinationRouteid;
  private Integer totalFirstSeats;
  private Integer totalEconomySeats;
  private String passangerInfo;

}
