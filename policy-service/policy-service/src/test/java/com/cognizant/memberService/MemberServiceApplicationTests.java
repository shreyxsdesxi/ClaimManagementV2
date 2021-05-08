package com.cognizant.memberService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.policyService.controller.PolicyController;

@SpringBootTest(classes = PolicyController.class)
//@WebMvcTest
class MemberServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@InjectMocks
	PolicyController policyController;

//	@Test
//	void contextLoads() {
//	}

	@Test
	public void testForGetEligibleBenefits() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/getEligibleClaimAmount/{policyId}/{memberId}", 1234, 1)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals("Cashless Medical Treatment", result.getResponse().getContentAsString());
	}

}
