package com.cognizant.memberservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.memberservice.model.MemberClaim;
import com.cognizant.memberservice.model.MemberPremium;
import com.cognizant.memberservice.model.Members;
import com.cognizant.memberservice.proxy.ClaimProxy;
import com.cognizant.memberservice.service.MembersServices;
import com.cognizant.memberservice.service.PremiumServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class MembersController {
	@Autowired
	MembersServices membersServices;
	@Autowired
	PremiumServices premiumServices;
	@Autowired
	ClaimProxy claimProxy;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MembersController.class);

	@GetMapping(value = "/viewBills/{policyId}/{MemberId}")
	public MemberPremium viewBills(@PathVariable int policyId, @PathVariable int MemberId) {
		Members members1 = membersServices.getMemberById(MemberId);
		MemberPremium memberPremium = premiumServices.premium(policyId, members1);
		System.out.println(memberPremium);
		return memberPremium;
	}

	@GetMapping(value = "/getClaimStatus/{policyId}/{memberId}/{claimNumber}")
	@Retry(name = "getClaimStatus", fallbackMethod = "fallBackMethodForGetClaimStatus")
	@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethodForGetClaimStatus")
	public MemberClaim getClaimStatus(@PathVariable String policyId, @PathVariable String memberId,
			@PathVariable String claimNumber) {
//		RestTemplate rt = new RestTemplate();
//		HashMap<String, String> uriVariables = new HashMap<>();
//		uriVariables.put("policyId", policyId);
//		uriVariables.put("memberId", memberId);
//		uriVariables.put("claimNumber", claimNumber);
//
//		ResponseEntity<MemberClaim> forEntity = rt.getForEntity(
//				"http://localhost:8000/getClaimStatus/{policyId}/{memberId}/{claimNumber}", MemberClaim.class, uriVariables);
//		MemberClaim result = forEntity.getBody();
//		return result;
		MemberClaim claimStatus = claimProxy.getClaimStatus(policyId, memberId, claimNumber);
		return claimStatus;
	}

	@PostMapping(value = "/submitClaim/{policyId}/{memberId}")
	public MemberClaim submitClaim(@PathVariable int policyId, @PathVariable int memberId,
			@RequestBody MemberClaim claim) {
//		RestTemplate rt = new RestTemplate();
//		HashMap<String, Integer> uriVariables = new HashMap<>();
//		uriVariables.put("policyId", policyId);
//		uriVariables.put("memberId", memberId);
//
//		MemberClaim postForObject = rt.postForObject("http://localhost:8000/submitClaim/{policyId}/{memberId}", claim,
//				MemberClaim.class, uriVariables);
//		return postForObject;
		MemberClaim submitClaim = claimProxy.submitClaim(policyId, memberId, claim);
		return submitClaim;
	}

	@GetMapping(value = "/viewPremium/{memberId}")
	//@Retry(name = "viewPremium", fallbackMethod = "fallBackMethodForViewPremium")
	public List<MemberPremium> viewPremium(@PathVariable int memberId) {
		LOGGER.info("viem Premium called");
		Members members1 = membersServices.getMemberById(memberId);
		Set<MemberPremium> premiumSet = members1.getPremium();
		List<MemberPremium> premiumList = new ArrayList<>();
		for (MemberPremium m : premiumSet) {
			premiumList.add(m);
		}
		//return null;
		return premiumList;
	}

	@GetMapping(value = "/getClaims/{memberId}")
	//@HystrixCommand(fallbackMethod = "fallBackMethodForGetAllClaims")
	@Retry(name = "getAllClaims", fallbackMethod = "fallBackMethodForGetAllClaims")
	@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethodForGetAllClaims")
	public List<MemberClaim> getAllClaims(@PathVariable int memberId) {

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("memberId", memberId);
		List<MemberClaim> findAllClaimByMemberID = claimProxy.findAllClaimByMemberID(memberId);

		return findAllClaimByMemberID;
	}
	
	//----------------------------------------------------
	
	//fallback method
	
	public List<MemberClaim> fallBackMethodForGetAllClaims( Exception exs) {
		LOGGER.info("Inside Fallback method");
		List<MemberClaim> response = new ArrayList<>();
		MemberClaim memberClaim = new MemberClaim();
		memberClaim.setStatus("Hard Coded value");
		memberClaim.setPolicyNumber(Integer.MAX_VALUE);
		response.add(memberClaim);
		return response;
	}
	
	
	public MemberClaim fallBackMethodForGetClaimStatus( Exception exs) {
		LOGGER.info("Inside Fallback method");
		MemberClaim memberClaim = new MemberClaim();
		memberClaim.setStatus("Hard Coded value");
		memberClaim.setPolicyNumber(Integer.MAX_VALUE);
		memberClaim.setStatus("Something went wrong");
		return memberClaim;
	}
	
	
}
