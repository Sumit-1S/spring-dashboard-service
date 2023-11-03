package com.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Component
public class Policy {
	private Integer policyId;
	private String policyName;
	private String policyDesc;
	private int duration;
	private PolicyType policyType;
}
