package com.wellsfargo.training.obs.model;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loginid;
	private String username;
	private String password;
	private long tpin;

	@OneToOne
	@JoinColumn(name = "u_id")
	private User us;
	
	
}
