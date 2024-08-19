package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pricesperkm")
public class PricePerKm extends BaseEntity
{
  
  private Integer firstClassPrice;
  private Integer economyClassPrice;
		
}
