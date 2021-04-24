package com.cognizant.memberservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.memberservice.model.MemberClaim;

@FeignClient(name="claim-service")
public interface ClaimProxy {
    @GetMapping(value="/getClaimStatus/{policyId}/{memberId}/{claimNumber}")
    public MemberClaim getClaimStatus(@PathVariable String policyId, @PathVariable String memberId,
			@PathVariable String claimNumber) ;
  @PostMapping(value="/submitClaim/{policyId}/{memberId}")  
  public MemberClaim submitClaim(@PathVariable int policyId, @PathVariable int memberId, @RequestBody MemberClaim claim);
  
}

