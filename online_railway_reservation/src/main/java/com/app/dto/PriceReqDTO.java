package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PriceReqDTO
{
	  private Integer firstClassPrice;
	  private Integer economyClassPrice;
}
