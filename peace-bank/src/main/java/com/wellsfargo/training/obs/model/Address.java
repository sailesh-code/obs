package com.wellsfargo.training.obs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressid;
	private String rstreet;
	private String rcity;
	private String rpincode;
	private String pstreet;
	private String pcity;
	private String ppincode;
	
	@OneToOne
	@JoinColumn(name = "user_id" )
	private User user;
}
