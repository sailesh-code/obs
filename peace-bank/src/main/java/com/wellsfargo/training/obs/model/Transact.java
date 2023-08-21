package com.wellsfargo.training.obs.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions_table")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private long id;
	
	private long fromAcc;
	
	private String benName;
	
	private long toAcc;
	
	private long amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date tranDate;
	
	private String nickName;
	
	private String remarks;
	
	private long pin;
}