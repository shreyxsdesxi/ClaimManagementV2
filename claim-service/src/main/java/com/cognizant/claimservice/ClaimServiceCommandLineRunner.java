package com.cognizant.claimservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cognizant.claimservice.model.Claims;
import com.cognizant.claimservice.repository.ClaimRepo;

@Component
public class ClaimServiceCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private ClaimRepo claimRepo;

	@Override
	public void run(String... args) throws Exception {
		Claims claim= new Claims();
		claim.setMemberId(1);
		claim.setStatus("Approved");
		claim.setPolicyNumber(1234);
		claim.setAmountClaimed(10000);
		claim.setAmountSettled(9500);
		claim.setBenifits("Coverage against medical expenses, Coverage against critical illnesses, Cashless claim benefit");
		claim.setHospitalId(1);
		claim.setHospitalDetails("Cambridge Hospital, Bangalore");
		claim.setRemarks("Claim closed");
		
		
		Claims claim2= new Claims();
		claim2.setMemberId(2);
		claim2.setStatus("Rejected");
		claim2.setPolicyNumber(9101);
		claim2.setAmountClaimed(620001);
		claim2.setAmountSettled(0);
		claim2.setBenifits("Coverage against medical expenses, Coverage against critical illnesses");
		claim2.setHospitalId(1);
		claim2.setHospitalDetails("Newlife Hospital, Kolkata");
		claim2.setRemarks("Claim closed");
		
		claimRepo.save(claim);
		claimRepo.save(claim2);
		
	}

}
