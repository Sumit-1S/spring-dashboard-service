package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.modelService.CartService;
import com.example.modelService.DatabaseService;
import com.example.modelService.DiscountService;
import com.example.modelService.UserService;
import com.model.CartEntity;
import com.model.Discount;
import com.model.DiscountRegisterRequest;
//import com.entity.Offers;
//import com.entity.Promotion;
//import com.entity.User;
//import com.repository.FeedbackRepository;
//import com.repository.FlightsRepository;
//import com.repository.OffersRepository;
//import com.repository.PromotionRepository;
//import com.repository.UserRepository;
import com.model.Policy;
import com.model.PolicyOwned;
import com.model.PolicyOwnedRequest;
import com.model.User;
import com.model.UserLoginRequest;
import com.model.UserPasswordResetRequest;
import com.model.UserRegisterRequest;

@RestController
@RequestMapping("/home")
@CrossOrigin("http://localhost:3002")
public class AppController {
		
		//Policy
		@Autowired
		private DatabaseService databaseService;
		
		@GetMapping("/getallpolicy")
		private List<Policy> getAllPolicy() {
			List<Policy> policyList = databaseService.getAllPolicy();
			System.out.println(policyList);
			return policyList;
		}
		
		@PostMapping("/addPolicy")
		private Policy createFlight(@RequestBody Policy policy) {
			System.out.println(policy);
			return databaseService.savePolicy(policy);
		}
		
		@GetMapping("/policyOwned/{id}")
		public List<PolicyOwned> getTicketData(@PathVariable String id) {
			return databaseService.findPolicyOwnedByClientUsername(id);
		}
		
		@GetMapping("/allPolicyOwned")
		public List<PolicyOwned> updateFlight() {
			return databaseService.findAllPolicyOwnedByClients();
		}
		
	
//		@DeleteMapping("/deleteflight/{id}")
//		public ResponseEntity<HttpStatus> deleteFlight(@PathVariable Long id) {
//			flightsRepo.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		
//		
//		//USER Login/Register/ForgetPassword
		@Autowired
		private UserService userService;
		
		@PostMapping("/verifyUser")
		private String verifyUser(@RequestBody UserLoginRequest user ) {
			return userService.verifyUser(user);
		}
		
		@PostMapping("/register")
		private String createUser(@RequestBody UserRegisterRequest user) {
			System.out.println(user);
			return userService.register(user);
		}

		@PutMapping("/updateuser")
		public String updareOffer(@RequestBody UserPasswordResetRequest user) {
			return userService.updatePassword(user);
		}
	
//		//OFFER
		@Autowired
		private DiscountService discountService;
		
		@GetMapping("/getalldiscount/{policyId}")
		private ArrayList<Discount> getAllOffers(@PathVariable Integer policyId){
			ArrayList<Discount> offerList = discountService.findAllDiscount(policyId);
			System.out.println(offerList);
			return offerList;
		}
		
		@PostMapping("/adddiscount")
		private String addDiscount(@RequestBody DiscountRegisterRequest discount) {
			System.out.println(discount);
			return discountService.addDiscount(discount);
		}
		
		@DeleteMapping("/deleteoffer/{discountId}")
		public String deleteOffer(@PathVariable Integer discountId) {
			return discountService.deleteById(discountId);
		}
		
		// Policy Cart
		@Autowired
		private CartService cartService;
		
		@GetMapping("/getcart/{clientUsername}")
		private CartEntity getCartPolicy(@PathVariable String clientUsername){
			CartEntity cartList = cartService.findPolicyByClient(clientUsername);
			return cartList;
		}
		
		@PostMapping("/purchasePolicy/{clientUsername}")
		private String purchasePolicy(@PathVariable String clientUsername,@RequestBody PolicyOwnedRequest ownedRequest){
			String response = cartService.purchasePolicy(clientUsername,ownedRequest);
			return response;
		}
		
//		//FEEDBACK
//		@Autowired
//		private FeedbackRepository feedRepo;
//		
//		@PostMapping("/createfeedback")
//		private Feedback createFeedback(@RequestBody Feedback feedback) {
//			System.out.println(feedback);
//			return feedRepo.save(feedback);
//		}
//		
//		@GetMapping("/allfeedback")
//		private List<Feedback> getAllFeedbacks(){
//			return feedRepo.findAll();
//			
//		}

}