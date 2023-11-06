package com.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CartEntity {
	private Integer cartId;
	private String clientUsername;
	private String policyId;
	private Double totalPremium;
}
