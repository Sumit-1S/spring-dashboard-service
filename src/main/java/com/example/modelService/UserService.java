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
import com.model.Policy;
import com.model.PolicyOwned;
import com.model.User;
import com.model.UserLoginRequest;
import com.model.UserPasswordResetRequest;
import com.model.UserRegisterRequest;

@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;
	
	public List<User> fetchAllUser() {
		return null;
	}

	public String register(UserRegisterRequest user) {
		String userServiceURL = "http://localhost:8000/loginService/register";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String userResponse = null;
		try {
			String json = ow.writeValueAsString(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			userResponse = restTemplate.postForObject(userServiceURL, request,String.class);
			
			System.out.println(user);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userResponse;
	}

	public String updatePassword(UserPasswordResetRequest user) {
		String userServiceURL = "http://localhost:8000/loginService/forgetpassword";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		ResponseEntity<String> userResponse = null;
		try {
			String json = ow.writeValueAsString(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			userResponse = restTemplate.exchange(userServiceURL, HttpMethod.PUT, request, String.class);

			System.out.println(user);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return userResponse.getBody();
	}

	public String verifyUser(UserLoginRequest user) {
		String userServiceURL = "http://localhost:8000/loginService/login";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String userResponse = null;
		try {
			String json = ow.writeValueAsString(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			userResponse = restTemplate.postForObject(userServiceURL, request,String.class);
			
			System.out.println(user);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userResponse;
	}

}
