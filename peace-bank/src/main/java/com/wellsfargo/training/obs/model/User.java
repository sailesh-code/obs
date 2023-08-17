package com.wellsfargo.training.obs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	@Column(name = "first_name" )
	private String fname;
	@Column(name = "middle_name")
	private String mname;
	@Column(name = "last_name")
	private String lname;
	@Column(name = "father_name")
	private String faname;
	@Column(name = "mother_name")
	private String moname;
	@Column(unique = true)
	private String email;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String dob;
	
	@Column(name = "Source_of_income")
	private String soi;
	
	@Column(name = "Gross_Salary")
	private long gs;
	@Column(name = "account_number")
	@NonNull
	private long anumber;
	
	@Column(name = "account_balance")
	@NonNull
	private long abalance;

	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
	private Address address;
	@OneToOne(mappedBy = "us" , cascade = CascadeType.ALL)
	private UserLogin userlogin;
	
	
	
	
	
}
