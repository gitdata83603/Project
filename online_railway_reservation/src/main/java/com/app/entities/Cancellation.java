package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cancellation")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "booking")
public class Cancellation extends BaseEntity
{
 
	   @Column(name = "reason_for_cancellation" ,length = 500)
	   private String reasonForCancellation;
	   @Column(name = "refund_amount")
	   private Double amount;
	   @Column(name = "date_of_cancellation")
	   private LocalDate dateOfcancellation;
	   
	   @ManyToOne/////////////////////////////////////////////////**************************************@OneToOne
	   @JoinColumn(name = "booking_id",nullable = false)
	   private Booking booking;//(@ManyToOne)
	
}
