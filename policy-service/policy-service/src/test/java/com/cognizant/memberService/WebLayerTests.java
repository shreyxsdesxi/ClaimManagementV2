package com.cognizant.memberService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.policyService.PolicyServiceApplication;
import com.cognizant.policyService.PolicyServiceCommandLineRunner;
import com.cognizant.policyService.controller.PolicyController;
import com.cognizant.policyService.entity.Member;
import com.cognizant.policyService.entity.Policy;
import com.cognizant.policyService.entity.Provider;
import com.cognizant.policyService.service.MemberService;
import com.cognizant.policyService.service.ProviderService;


@ContextConfiguration(classes = {PolicyServiceApplication.class, PolicyServiceCommandLineRunner.class})
@WebMvcTest(PolicyController.class)
@ActiveProfiles("test")
class WebLayerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	PolicyController policyController;
	
	@MockBean
	ProviderService providerService;
	
	@MockBean
	MemberService memberService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(policyController).build();
	}
	
	@Test
	public void testForGetListOfProviders() throws Exception {

		Provider provider = new Provider();
		provider.setHospitalId(1);
		provider.setHospitalName("New Newlife Hospital");
		provider.setLocation("Kolkata");

		Mockito.when(providerService.getProviderById(1)).thenReturn(provider);

		RequestBuilder request = MockMvcRequestBuilders.get("/getProviderById/1")
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(content().json("{\"hospitalId\":1,\"hospitalName\":\"New Newlife Hospital\",\"location\":\"Kolkata\"}"));
		
	}
	
	@Test
	public void testForGetElegibleBenefits() throws Exception {
		
		Policy policy = new Policy();
		policy.setBenefits("Up to 4 family members " + 
				"No of Members Covered " + 
				"Rs. 60 lakhs " + 
				"Maximum sum assured");
		policy.setElegibleAmount(10000);
		policy.setPolicyNumber(1234);
		policy.setPolicyName("HDFC Health Insurance");
		policy.setPremium(10000);
		policy.setBenefits("Cashless Medical Treatment");
		
		Policy policy2 = new Policy();
		policy2.setBenefits("Up to 6 family members " + 
				"No of Members Covered " + 
				"Rs. 50 lakhs " + 
				"Maximum sum assured");
		policy2.setElegibleAmount(620000);
		policy2.setPolicyNumber(5678);
		policy2.setPolicyName("SBI Health Insurance");
		policy2.setPremium(5000);
		policy2.setBenefits("Coverage of Pre and Post-Hospitalization Expenses");
		
		Member member = new Member();
		member.setMemberId(1);
		
		policy.getMemberList().add(member);
		member.getPolicyMembersList().add(policy);
		policy2.getMemberList().add(member);
		member.getPolicyMembersList().add(policy2);
		
		Mockito.when(memberService.getMemberById(1)).thenReturn(member);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/getEligibleBenefits/1234/1")
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(content().string("Cashless Medical Treatment"));
		
		
	}
	
	@Test
	public void testGetElegibleAmount() throws Exception {
		
		Policy policy = new Policy();
		policy.setBenefits("Up to 4 family members " + 
				"No of Members Covered " + 
				"Rs. 60 lakhs " + 
				"Maximum sum assured");
		policy.setElegibleAmount(10000);
		policy.setPolicyNumber(1234);
		policy.setPolicyName("HDFC Health Insurance");
		policy.setPremium(10000);
		policy.setBenefits("Cashless Medical Treatment");
		
		Policy policy2 = new Policy();
		policy2.setBenefits("Up to 6 family members " + 
				"No of Members Covered " + 
				"Rs. 50 lakhs " + 
				"Maximum sum assured");
		policy2.setElegibleAmount(620000);
		policy2.setPolicyNumber(5678);
		policy2.setPolicyName("SBI Health Insurance");
		policy2.setPremium(5000);
		policy2.setBenefits("Coverage of Pre and Post-Hospitalization Expenses");
		
		Member member = new Member();
		member.setMemberId(1);
		
		policy.getMemberList().add(member);
		member.getPolicyMembersList().add(policy);
		policy2.getMemberList().add(member);
		member.getPolicyMembersList().add(policy2);
		
		Mockito.when(memberService.getMemberById(1)).thenReturn(member);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/getEligibleClaimAmount/1234/1")
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(content().string("10000"));
		

	}
	
	

}
