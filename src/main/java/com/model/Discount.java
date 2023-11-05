package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discount {
	private Integer discountId;
	private Integer policyId;
	private Double discountAmount;
	private Boolean active;
}
