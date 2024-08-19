package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cancellation_policy ")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CancellationPolicy extends BaseEntity
{
	
	
    @Column(name = "days_of_cncellation_policy",columnDefinition = "Integer default 1")	
	private Integer days;
	
}
