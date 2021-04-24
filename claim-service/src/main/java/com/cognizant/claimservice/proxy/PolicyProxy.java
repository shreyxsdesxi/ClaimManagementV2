package com.cognizant.claimservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.claimservice.model.Provider;



@FeignClient(name="policy-service")
public interface PolicyProxy {
	
	@GetMapping(value="/getEligibleClaimAmount/{policyId}/{memberId}")
	public ResponseEntity<Integer> getElegibleClaim(@PathVariable int policyId, @PathVariable int memberId);
	@GetMapping(value = "/getChainOfProviders/{policyId}")
	public List<Provider> findAllProvidersByPolicyId(@PathVariable int policyId);
	

}
