package com.app.dto;

import javax.persistence.Column;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StationReqDTO 
{
	 private String code;
	 private String name;
}
