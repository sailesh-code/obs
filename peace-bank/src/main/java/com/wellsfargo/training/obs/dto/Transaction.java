package com.wellsfargo.training.obs.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
private long id;
	
	private long fromAcc;
	
	private String benName;
	
	private long toAcc;
	
	private long amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date tranDate;
	
	private String nickName;
	
	private String remarks;
	
	private String type;
	
	private long pin;
}
