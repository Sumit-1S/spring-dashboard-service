package com.example.modelService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.model.Discount;
import com.model.DiscountRegisterRequest;
import com.model.Policy;

@Service
public class DiscountService {

	@Autowired
	RestTemplate restTemplate;
	
	public ArrayList<Discount> findAllDiscount(Integer policyId) {
		String discountServiceURL = "http://localhost:8020/discountService/getalldiscount/"+policyId;
		
		ResponseEntity<Discount[]> disResponse = restTemplate.getForEntity(discountServiceURL, Discount[].class);
		Discount[] disList = disResponse.getBody();
		
		System.out.println(discountServiceURL);
		System.out.println(disList);
		
		ArrayList<Discount> discountListResponse = new ArrayList();
	
		for(int i=0;i<disList.length;i++) {
			Discount l = disList[i];
			discountListResponse.add(l);
		}
		
		return discountListResponse;
	}

	public String addDiscount(DiscountRegisterRequest discount) {
		String policyServiceURL = "http://localhost:8020/discountService/adddiscount";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String policyResponse = null;
		try {
			String json = ow.writeValueAsString(discount);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			policyResponse = restTemplate.postForObject(policyServiceURL, request, String.class);
			System.out.println(policyServiceURL);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return policyResponse;
	}

	public String deleteById(Integer discountId) {
		String userServiceURL = "http://localhost:8020/discountService/deleteoffer/"+discountId;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		ResponseEntity<String> userResponse = null;
		try {
			String json = ow.writeValueAsString(discountId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			userResponse = restTemplate.exchange(userServiceURL, HttpMethod.PUT, request, String.class);

			System.out.println(discountId);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return userResponse.getBody();
	}

}
