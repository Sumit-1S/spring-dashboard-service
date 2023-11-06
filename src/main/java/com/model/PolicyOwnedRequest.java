package com.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PolicyOwnedRequest {
	private int clientAge;
	private String clientCity;
	private String clientMedicalHistory;
	private Boolean clientTobaccoUser;
}
