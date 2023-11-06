package com.example.modelService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.model.CartEntity;
import com.model.Discount;
import com.model.DiscountRegisterRequest;
import com.model.Policy;
import com.model.PolicyOwned;
import com.model.PolicyOwnedRequest;
import com.model.Status;

@Service
public class CartService {

	@Autowired
	RestTemplate restTemplate;

	static List<Integer> methodParsePolicyIdString(String str)
    {
 
        String[] splitArray = str.split(" ");
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < splitArray.length; i++) {
            array.add(Integer.parseInt(splitArray[i]));
        }
        return array;
    }
	
	public CartEntity findPolicyByClient(String clientUsername) {
		String cartServiceURL = "http://localhost:8030/insuranceCart/getCartByUsername/"+clientUsername;
		ResponseEntity<CartEntity> cartResponse = restTemplate.getForEntity(cartServiceURL, CartEntity.class);
		CartEntity cart = cartResponse.getBody();
		return cart;
	}

	public String purchasePolicy(String clientUsername,PolicyOwnedRequest ownedRequest) {
		String cartServiceURL = "http://localhost:8030/insuranceCart/getCartByUsername/"+clientUsername;
		ResponseEntity<CartEntity> cartResponse = restTemplate.getForEntity(cartServiceURL, CartEntity.class);
		CartEntity cart = cartResponse.getBody();
		
		String policyIdStr = cart.getPolicyId();		
		
		String policyServiceURL = "http://localhost:8070/policyApp/getAllPolicyByList/"+policyIdStr;
		System.out.println(policyServiceURL);
		ResponseEntity<Policy[]> policyResponse = restTemplate.getForEntity(policyServiceURL, Policy[].class);
		System.out.println(policyResponse.getBody());
		Policy[] policyArr = policyResponse.getBody();
		List<PolicyOwned> policyOwnedList = new ArrayList();
		for(int i=0;i<policyArr.length;i++) {
			Policy policy = policyArr[i]; 
			String discountServiceURL = "http://localhost:8020/discountService/getDiscount/"+policy.getPolicyId();
			ResponseEntity<Discount[]> discountResponse = restTemplate.getForEntity(discountServiceURL, Discount[].class);
			System.out.println(discountResponse.getBody());
			PolicyOwned policyOwned = new PolicyOwned();
			policyOwned.setClientUsername(clientUsername);
			policyOwned.setClientAge(ownedRequest.getClientAge());
			policyOwned.setClientCity(ownedRequest.getClientCity());
			policyOwned.setClientMedicalHistory(ownedRequest.getClientMedicalHistory());
			policyOwned.setDate(new java.sql.Date(System.currentTimeMillis()));
			policyOwned.setPremium(null);
			policyOwned.setStatus(Status.ACTIVE);
			policyOwned.setClientTobaccoUser(ownedRequest.getClientTobaccoUser());
			policyOwned.setPolicy(policy);
			policyOwnedList.add(policyOwned);
		}
		String policyOwnedServiceURL = "http://localhost:8070/policyOwnedApp/applyPolicyList";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String policyOwnedResponse = null;
		try {
			String json = ow.writeValueAsString(policyOwnedList);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			policyOwnedResponse = restTemplate.postForObject(policyOwnedServiceURL, request, String.class);			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "Purchase Done...";
	}
	
}
