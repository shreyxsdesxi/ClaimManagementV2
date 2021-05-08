package com.example.memberservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.memberservice.MemberServiceApplication;
import com.cognizant.memberservice.MemberServiceCommandLineRunner;
import com.cognizant.memberservice.controller.MembersController;
import com.cognizant.memberservice.model.MemberPremium;
import com.cognizant.memberservice.model.Members;
import com.cognizant.memberservice.proxy.ClaimProxy;
import com.cognizant.memberservice.service.MembersServices;
import com.cognizant.memberservice.service.PremiumServices;

@ContextConfiguration(classes = {MemberServiceApplication.class, MemberServiceCommandLineRunner.class})
@WebMvcTest(MembersController.class)
@ActiveProfiles("test")
class WebLayerTests {

	///viewBills/{policyId}/{MemberId}
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	MembersServices memberService;
	
	@MockBean
	PremiumServices premiumService;
	
	@Autowired
	MembersController memberController;
	
	@MockBean
	ClaimProxy claimProxy;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
	}
	
	@Test
	public void testForViewBills() throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Members member = new Members();
		member.setFirstname("Shreyas");
		member.setLastname("Desai");
		member.setAge(22);
		member.setEmail("889157@cognizant.com");

		MemberPremium premium = new MemberPremium();
		Date due = sdf.parse("2021-04-30");
		Date lastPaidDate = sdf.parse("2021-03-30");
		premium.setDue(due);
		premium.setLastPaidDate(lastPaidDate);
		premium.setLatePaymentCharges(1000);
		premium.setAmount(25000);
		premium.setPolicyId(1234);
		
		Mockito.when(memberService.getMemberById(1)).thenReturn(member);
		Mockito.when(premiumService.premium(1234, member)).thenReturn(premium);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/viewBills/{policyId}/{MemberId}", 1234, 1)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(content().json("{\"id\":0,\"due\":\"2021-04-29T18:30:00.000+00:00\",\"amount\":25000,\"policyId\":1234,\"lastPaidDate\":\"2021-03-29T18:30:00.000+00:00\",\"latePaymentCharges\":1000,\"members\":null}"));
		
	}

}
