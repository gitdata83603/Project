package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "bookings")
public class User extends BaseEntity 
{
	@Column(name = "first_name", length = 25) // col name , varchar(25)
	private String firstName;
	@Column(name = "last_name", length = 25) // col name , varchar(25)
	private String lastName;
	@Column(length = 20, unique = true) // varchar(20), unique constraint
	private String email;
	@Column(length = 25, nullable = false) // NOT NULL
	private String password;
	private String address;	
	@Enumerated(EnumType.STRING) // col type : varchar
	@Column(length = 20)
	private Role role;

	/*
	 private String firstName;
	 private String email;
	 */
	
	/*
	 @OneToMany
	 List<Booking> bookings=new ArrayList<Booking>();

	 */

}