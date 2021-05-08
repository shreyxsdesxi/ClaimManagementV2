package com.example.memberservice;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.cognizant.memberservice.MemberServiceApplication;
import com.cognizant.memberservice.MemberServiceCommandLineRunner;
import com.cognizant.memberservice.model.MemberPremium;
import com.cognizant.memberservice.model.Members;
import com.cognizant.memberservice.repository.MemberPremiumRepository;
import com.cognizant.memberservice.repository.MembersRepository;

@DataJpaTest
@ContextConfiguration(classes = { MemberServiceApplication.class, MemberServiceCommandLineRunner.class })
@ActiveProfiles("test")
class DataLayerTests {

	@Autowired
	MembersRepository memberRepository;

	@Autowired
	MemberPremiumRepository memberPremiumRepository;

	@Test
	public void testForGetMemberById() throws ParseException {

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
		
		MemberPremium findByPolicyIdAndMembers = memberPremiumRepository.findByPolicyIdAndMembers(1, member);
		assertEquals(1000, findByPolicyIdAndMembers.getLatePaymentCharges());
	}

}
