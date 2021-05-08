package com.cognizant.claimservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.claimservice.controller.ClaimController;
import com.cognizant.claimservice.model.Claims;
import com.cognizant.claimservice.model.Provider;
import com.cognizant.claimservice.proxy.PolicyProxy;
import com.cognizant.claimservice.service.ClaimService;


@ContextConfiguration(classes = {ClaimServiceApplication.class, ClaimServiceCommandLineRunner.class})
//@ActiveProfiles("test")
//@SpringBootTest(classes = PolicyServiceApplication.class, webEnvironment = WebEnvironment.MOCK)
//@RunWith(SpringRunner.class)
@WebMvcTest(ClaimController.class)
@ActiveProfiles("test")
class WebLayerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ClaimService claimService;
	
	@Autowired
	ClaimController claimController;
	
	@MockBean
	PolicyProxy policyProxy;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(claimController).build();
	}
	
	@Test
	public void testForGetClaimStatus() throws Exception {
		
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
		
		Mockito.when(claimService.getClaim(1, 1, 1234)).thenReturn(claim);
		
		RequestBuilder accept = MockMvcRequestBuilders.get("/getClaimStatus/{policyId}/{memberId}/{claimNumber}", 1234, 1, 1).accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(accept).andExpect(content().string("{\"claimNumber\":0,\"status\":\"Approved\",\"remarks\":\"Claim closed\",\"policyNumber\":1234,\"hospitalDetails\":\"Cambridge Hospital, Bangalore\",\"hospitalId\":1,\"benifits\":\"Coverage against medical expenses, Coverage against critical illnesses, Cashless claim benefit\",\"amountClaimed\":10000,\"amountSettled\":9500,\"memberId\":1,\"action\":null}"));
	}
	
	@Test
	public void testSubmitClaim() throws Exception {

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
		
		Provider provider = new Provider();
		provider.setHospitalName("Red Cross Hospital");
		provider.setLocation("Pune");
		List<Provider> providerList = new ArrayList<>();
		providerList.add(provider);
		
		ResponseEntity<String> response = new ResponseEntity<String>("Approved", HttpStatus.OK);
		ResponseEntity<Integer> response1 = new ResponseEntity<Integer>(10000, HttpStatus.OK);
		
		Mockito.when(policyProxy.findAllProvidersByPolicyId(1234)).thenReturn(providerList);
		Mockito.when(policyProxy.getBenefitsOfPolicy(1234, 1)).thenReturn(response);
		Mockito.when(policyProxy.getElegibleClaim(1234, 1)).thenReturn(response1);
		
		RequestBuilder accept = MockMvcRequestBuilders.post("/submitClaim/{policyId}/{memberId}", 1234, 1, claim).accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(accept).andExpect(content().string("Approved"));
		
	}
	

}
