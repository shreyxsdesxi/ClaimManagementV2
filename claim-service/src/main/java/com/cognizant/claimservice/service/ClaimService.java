package com.cognizant.claimservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.claimservice.model.Claims;
import com.cognizant.claimservice.repository.ClaimRepo;



@Service
public class ClaimService {
	
	@Autowired
	ClaimRepo crepository;

//	public static List getALLClaimByClaimId(int claimId) {
//		return ClaimService.getALLClaimByClaimId(claimId);
//	}
//
//	public static List getALLClaimByMemberId(int memberId) {
//		return ClaimService.getALLClaimByMemberId(memberId);
//	}
//
//	public static List getALLClaimByPolicyId(int policyId) {
//		return ClaimService.getALLClaimByPolicyId(policyId);
//	}
	
	public Claims getClaim(int claimNumber, int memberId, int policyNumber) {
		return crepository.findByClaimNumberAndMemberIdAndPolicyNumber(claimNumber, memberId, policyNumber);
	}
	
	public void saveClaim(Claims claim) {
		crepository.save(claim);
	}
	
	public List<Claims> getALLClaimByMemberId(int memberId) {
		return crepository.findByMemberId(memberId);
	}


}
