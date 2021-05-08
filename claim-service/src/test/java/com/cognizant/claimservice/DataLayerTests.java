package com.cognizant.claimservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.claimservice.model.Claims;
import com.cognizant.claimservice.repository.ClaimRepo;

@DataJpaTest
@ContextConfiguration(classes = {ClaimServiceApplication.class, ClaimServiceCommandLineRunner.class})
@ActiveProfiles("test")
class DataLayerTests {

	@Autowired
	ClaimRepo claimRepo;
	
	@Test
	public void testForFindByPolicyNumberAndClaimNumberAndMemberId() {
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
		
		Claims findByClaimNumberAndMemberIdAndPolicyNumber = claimRepo.findByClaimNumberAndMemberIdAndPolicyNumber(1, 1, 1234);
		assertEquals(claim.getStatus(), findByClaimNumberAndMemberIdAndPolicyNumber.getStatus());
		
	}

}
