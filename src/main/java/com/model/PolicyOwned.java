package com.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class PolicyOwned {
	private Integer policyOwnedId;
	private String clientUsername;
	private int clientAge;
	private String clientCity;
	private String clientMedicalHistory;
	private Date date;
	private Double premium;
	private Status status;
	private Boolean clientTobaccoUser;
	private Policy policy;
	
	
	

}
