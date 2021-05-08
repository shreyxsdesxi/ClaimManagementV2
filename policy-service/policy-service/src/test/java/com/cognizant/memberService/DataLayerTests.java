package com.cognizant.memberService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.policyService.PolicyServiceApplication;
import com.cognizant.policyService.PolicyServiceCommandLineRunner;
import com.cognizant.policyService.entity.Policy;
import com.cognizant.policyService.entity.Provider;
import com.cognizant.policyService.repository.PolicyRepository;
import com.cognizant.policyService.repository.ProviderRepository;

@DataJpaTest
@ContextConfiguration(classes = {PolicyServiceApplication.class, PolicyServiceCommandLineRunner.class})
@ActiveProfiles("test")
class DataLayerTests {
	
	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	ProviderRepository providerRepository;
	
	@Test
	public void testForGetPolicyById() {
		
		Policy policy = new Policy();
		policy.setElegibleAmount(10000);
		policy.setPolicyNumber(1234);
		policy.setPolicyName("HDFC Health Insurance");
		policy.setPremium(10000);
		policy.setBenefits("Cashless Medical Treatment");
		
		Policy findByPolicyId = policyRepository.findByPolicyId(1);
		System.out.println(findByPolicyId);
		assertEquals(policy, findByPolicyId);
	}
	
	@Test
	public void testForGetProviderById() {
		Provider provider = providerRepository.findById(1).get();
		assertEquals("Kolkata", provider.getLocation());
	}

}
