package com.wellsfargo.training.obs.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceUpdate {
	private String action;
	private long amount;
}
