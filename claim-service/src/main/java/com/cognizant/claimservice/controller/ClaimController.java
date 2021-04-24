package com.cognizant.claimservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cognizant.claimservice.model.Claims;
import com.cognizant.claimservice.model.Provider;
import com.cognizant.claimservice.proxy.PolicyProxy;
import com.cognizant.claimservice.service.ClaimService;



@RestController
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	@Autowired PolicyProxy policyProxy;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClaimController.class); 
	
//	@GetMapping ("/getClaimStatus/{claimId}")
//	public <Claim> List findAllClaimByClaimID(@PathVariable int claimId) {
//		return ClaimService.getALLClaimByClaimId(claimId);
//		
//	}
//
//	@GetMapping ("/getClaimStatus/{memberId}")
//	public <Claim> List findAllClaimByMemberID(@PathVariable int memberId) {
//		return ClaimService.getALLClaimByMemberId(memberId);	
//	}
//	@GetMapping ("/getClaimStatus/{policyId}")
//	public <Claim> List findAllClaimByPolicyID(@PathVariable int policyId) {
//		return ClaimService.getALLClaimByPolicyId(policyId);
//		
//	}
	
	@GetMapping(value = "/getClaimStatus/{policyId}/{memberId}/{claimNumber}")
	public Claims getStatus(@PathVariable String policyId, @PathVariable String memberId, @PathVariable String claimNumber) {
		int c  = Integer.parseInt(claimNumber);
		int p = Integer.parseInt(policyId);
		int m = Integer.parseInt(memberId);
		Claims claim = claimService.getClaim(c, m, p);
		return claim;
	}
	
	@PostMapping(value = "/submitClaim/{policyId}/{memberId}")
	public Claims submitClaim(@PathVariable int policyId, @PathVariable int memberId, @RequestBody Claims claim) {
		//claimService.saveClaim(claim);
		
		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("policyId", policyId);
		uriVariables.put("memberId", memberId);
		
//		RestTemplate rt = new RestTemplate();
//		ResponseEntity<Integer> forEntity = rt.getForEntity("http://localhost:8002/getEligibleClaimAmount/{policyId}/{memberId}", Integer.class, uriVariables);
//		int elegibleAmount = forEntity.getBody();
		List<Provider> findAllProvidersByPolicyId = policyProxy.findAllProvidersByPolicyId(policyId);
		
//		ResponseEntity<Provider[]> forEntity2 = rt.getForEntity("http://localhost:8002/getChainOfProviders/{policyId}", Provider[].class, uriVariables);
//		Provider[] body = forEntity2.getBody();
		ResponseEntity<Integer> elegibleClaim = policyProxy.getElegibleClaim(policyId, memberId);
		Integer elegibleAmount = elegibleClaim.getBody();
		
		int claimedAmount = claim.getAmountClaimed();
		int hospitalId  = claim.getHospitalId();
		
		HashMap<String, Integer> uriVariables2 = new HashMap<>();
		uriVariables2.put("id", hospitalId);
		
//		ResponseEntity<Provider> forHospital = rt.getForEntity("http://localhost:8002/getProviders/{id}", Provider.class, uriVariables2);
//		Provider hospital = forHospital.getBody();
		
//		LOGGER.info("Hospital name: {}", hospital);
		
		LOGGER.info("Elegible amount: {}", elegibleAmount);
//		LOGGER.info("Hospitals: {}", body);
		LOGGER.info("claimedAmount: {}", claimedAmount);
		LOGGER.info("hospitalId {}", hospitalId);
		
		boolean claimFlag = false;
		boolean hospitalFlag = false;
		String hospitalName = "";
		
		if(elegibleAmount >= claimedAmount) {
			claimFlag = true;
		}
		
		for(int i = 0; i < findAllProvidersByPolicyId.size(); i++) {
			if(findAllProvidersByPolicyId.get(i).getHospitalId() == hospitalId) {
				hospitalFlag = true;
				hospitalName = findAllProvidersByPolicyId.get(i).getHospitalName();
				break;
			}
		}
		
		String result = "In process";
		
		if(claimedAmount < 0) {
			result = "Insufficient Claim details";
		}
		else {
			if(claimFlag == true && hospitalFlag ==true) {
				result = "Pending Action";
			}
			else if(claimFlag == false || hospitalFlag == false){
				result = "Claim Rejected";
			}
			else {
				result = "Under Despute";
			}
		}
		
		claim.setStatus(result);
		claim.setPolicyNumber(policyId);
		claim.setMemberId(memberId);
		claim.setHospitalId(hospitalId);
//		claim.setHospitalDetails(hospital.getHospitalName());
		
		
		claimService.saveClaim(claim);
		return claim;
			
	}
	
	@GetMapping ("/getClaims/{memberId}")
	public List<Claims> findAllClaimByMemberID(@PathVariable int memberId) {
		return claimService.getALLClaimByMemberId(memberId);	
	}
	
}
