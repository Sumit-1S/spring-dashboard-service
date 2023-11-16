package com.example.modelService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.model.Policy;
import com.model.PolicyOwned;
import com.netflix.discovery.converters.Auto;



@Service
public class DatabaseService {

	@Autowired
	RestTemplate restTemplate;
	
	public List<Policy> getAllPolicy(){
		String policyServiceURL = "http://localhost:8070/policyApp/getAllPolicy";
		
		ResponseEntity<Policy[]> policyResponse = restTemplate.getForEntity(policyServiceURL, Policy[].class);
		Policy[] policyList = policyResponse.getBody();
		
		System.out.println(policyServiceURL);
		System.out.println(policyList);
		
		List<Policy> policyListResponse = new ArrayList();
	
		for(int i=0;i<policyList.length;i++) {
			Policy l = policyList[i];
			policyListResponse.add(
					Policy.builder()
					.policyId(l.getPolicyId())
					.policyDesc(l.getPolicyDesc())
					.policyName(l.getPolicyName())
					.policyType(l.getPolicyType())
					.duration(l.getDuration())
					.build()
					);
		}
		
		return policyListResponse;
	}

	public Policy savePolicy(Policy policy) {
		String policyServiceURL = "http://localhost:8070/policyApp/addPolicy";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Policy policyResponse = null;
		try {
			String json = ow.writeValueAsString(policy);
			System.out.println(policy);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			policyResponse = restTemplate.postForObject(policyServiceURL, request, Policy.class);
			System.out.println(policyServiceURL);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return policyResponse;
	}

	public List<PolicyOwned> findPolicyOwnedByClientUsername(String id) {
		String policyServiceURL = "http://localhost:8070/policyOwnedApp/getAllPolicyByClientUsername/"+id;
		
		ResponseEntity<PolicyOwned[]> policyOwnedResponse = restTemplate.getForEntity(policyServiceURL, PolicyOwned[].class);
		PolicyOwned[] policyList = policyOwnedResponse.getBody();
		
		System.out.println(policyServiceURL);
		System.out.println(policyList);
		
		List<PolicyOwned> policyOwnedListResponse = new ArrayList();
	
		for(int i=0;i<policyList.length;i++) {
			PolicyOwned l = policyList[i];
			policyOwnedListResponse.add(l);
		}
		
		return policyOwnedListResponse;
	}

	public List<PolicyOwned> findAllPolicyOwnedByClients() {
String policyServiceURL = "http://localhost:8070/policyOwnedApp/getAllAppliedPolicy";
		
		ResponseEntity<PolicyOwned[]> policyOwnedResponse = restTemplate.getForEntity(policyServiceURL, PolicyOwned[].class);
		PolicyOwned[] policyList = policyOwnedResponse.getBody();
		
		System.out.println(policyServiceURL);
		System.out.println(policyList);
		
		List<PolicyOwned> policyOwnedListResponse = new ArrayList();
	
		for(int i=0;i<policyList.length;i++) {
			PolicyOwned l = policyList[i];
			policyOwnedListResponse.add(l);
		}
		
		return policyOwnedListResponse;
	}
}
