package com.example.controller;

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

import com.example.modelService.DatabaseService;
import com.example.modelService.OfferService;
import com.example.modelService.UserService;
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
import com.model.User;
import com.model.UserPasswordResetRequest;
import com.model.UserRegisterRequest;

@RestController
@RequestMapping("/home")
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
//		//USER
		@Autowired
		private UserService userService;
		
//		@GetMapping("/alluser")
//		private List<User> getAllUsers() {
//			List<User> userList = userService.findAll();
//			System.out.println(userList);
//			return userService.fetchAllUser();
//		}
		
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
		private OfferService offerService;
		
//		@GetMapping("/alloffers")
//		private List<Offers> getAllOffers(){
//			List<Offers> offerList = offerRepo.findAll();
//			System.out.println(offerList);
//			return offerRepo.findAll();
//		}
//		
//		@PostMapping("/createoffer")
//		private Offers createOffer(@RequestBody Offers offer) {
//			System.out.println(offer);
//			return offerRepo.save(offer);
//		}
//		
//		@GetMapping("/offerbyid/{id}")
//		public Optional<Offers> getOfferById(@PathVariable int id) {
//			return offerRepo.findById(id);
//		}
//		
//		@PutMapping("/updateoffer")
//		public Offers updareOffer(@RequestBody Offers offer) {
//			return offerRepo.save(offer);
//		}
//		
//		@DeleteMapping("/deleteoffer/{id}")
//		public ResponseEntity<HttpStatus> deleteOffer(@PathVariable int id) {
//			offerRepo.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		
//		
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
//		
//		
//		//PROMOTION 
//		@Autowired
//		private PromotionRepository promoRepo;
//		
//		@GetMapping("/allpromotions")
//		private List<Promotion> getAllPromotions(){
//			List<Promotion> promoList =promoRepo.findAll();
//			System.out.println(promoList);
//			return promoRepo.findAll();
//		}
//		
//		@GetMapping("/promotionbyid/{id}")
//		public Optional<Promotion> getPromotionById(@PathVariable int id) {
//			return promoRepo.findById(id);
//		}
//		
//		@PostMapping("/createpromotion")
//		private Promotion createPromotion(@RequestBody Promotion promo) {
//			System.out.println(promo);
//			return promoRepo.save(promo);
//		}	
//		
//		@PutMapping("/updatepromotion")
//		public Promotion updarePromotion(@RequestBody Promotion promotion) {
//			return promoRepo.save(promotion);
//		}
//		
//	
//		@DeleteMapping("/deletepromotion/{id}")
//		public ResponseEntity<HttpStatus> deletePromotion(@PathVariable int id) {
//			promoRepo.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
	
}