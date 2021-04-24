package com.cognizant.claimservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.claimservice.model.Claims;
@Repository
public interface ClaimRepo extends JpaRepository<Claims,Integer>{
	Claims findByClaimNumberAndMemberIdAndPolicyNumber(int claimNumber, int memberId, int policyNumber);
	
	List<Claims> findByMemberId(int memberId);
}
