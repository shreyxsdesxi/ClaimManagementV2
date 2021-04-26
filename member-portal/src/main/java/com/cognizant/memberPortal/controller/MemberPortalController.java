package com.cognizant.memberPortal.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cognizant.memberPortal.model.LoginRequest;
import com.cognizant.memberPortal.model.LoginResponse;
import com.cognizant.memberPortal.model.MemberClaim;
import com.cognizant.memberPortal.model.MemberPremium;
import com.cognizant.memberPortal.model.User;

@Controller
public class MemberPortalController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberPortalController.class);

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/viewBills/{policyId}")
	public String viewBills(@PathVariable int policyId, ModelMap map, HttpSession session) {

		String token = (String) session.getAttribute("token");
		String jwtToken = "Bearer " + token;

		int memberId = (int) session.getAttribute("userId");
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("policyId", policyId);
		uriVariables.put("MemberId", memberId);

//		ResponseEntity<MemberPremium> forEntity = restTemplate.getForEntity(
//				"http://localhost:9090/api/member-service/viewBills/{policyId}/{MemberId}", MemberPremium.class,
//				uriVariables, header);
//		MemberPremium result = forEntity.getBody();

		HttpEntity<String> httpEntity = new HttpEntity<String>(header);

		ResponseEntity<MemberPremium> exchange = restTemplate.exchange(
				"http://localhost:9090/api/member-service/viewBills/{policyId}/{MemberId}", HttpMethod.GET, httpEntity,
				MemberPremium.class, uriVariables);
		MemberPremium result = exchange.getBody();

		map.put("result", result);
		LOGGER.info("List of Bills: {}", result);

		return "viewBills";

	}

	@GetMapping(value = "/viewPremium")
	public String viewPremiums(ModelMap map, HttpSession session) {

		int memberId = (int) session.getAttribute("userId");
		String token = (String) session.getAttribute("token");

		LOGGER.info("Token: {}", token);

		String jwtToken = "Bearer " + token;

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);

		LOGGER.info("MemberID: {}", memberId);

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("memberId", memberId);

		// ResponseEntity<MemberPremium[]> forEntity = restTemplate.getForEntity(
		// "http://localhost:9090/api/member-service/viewPremium/{memberId}",
		// MemberPremium[].class, uriVariables);
		// MemberPremium[] result = forEntity.getBody();

		HttpEntity<String> entity = new HttpEntity<String>(header);
		ResponseEntity<MemberPremium[]> exchange = restTemplate.exchange(
				"http://localhost:9090/api/member-service/viewPremium/{memberId}", HttpMethod.GET, entity,
				MemberPremium[].class, uriVariables);
		MemberPremium[] result = exchange.getBody();

		LOGGER.info("result: {}", result);

		map.put("result", result);
		// return result;
		return "viewPremium";

	}

	@GetMapping(value = "/submitClaim")
	public String submitClaim(ModelMap map, HttpSession session) {

		int memberId = (int) session.getAttribute("userId");
		String token = (String) session.getAttribute("token");

		LOGGER.info("Token: {}", token);

		String jwtToken = "Bearer " + token;

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);

		LOGGER.info("MemberID: {}", memberId);

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("memberId", memberId);

		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		ResponseEntity<MemberPremium[]> exchange = restTemplate.exchange("http://localhost:9090/api/member-service/viewPremium/{memberId}", HttpMethod.GET, entity, MemberPremium[].class, uriVariables);
		MemberPremium[] result = exchange.getBody();
		
		LOGGER.info("result: {}", result);

		map.put("policy", result);

		return "submitClaim";
	}

	@PostMapping(value = "/submitClaim")
	public String submitClaimPost(@RequestParam int amount, @RequestParam int hospitalId, @RequestParam int policyId,
			ModelMap map, HttpSession session, @RequestParam String benefits) {

		LOGGER.info("Benefit availed: {}", benefits);
		
		MemberClaim claim = new MemberClaim();
		claim.setHospitalId(hospitalId);
		claim.setAmountClaimed(amount);
		claim.setBenifits(benefits);

		int memberId = (int) session.getAttribute("userId");
		String token = (String) session.getAttribute("token");

		LOGGER.info("Token: {}", token);

		String jwtToken = "Bearer " + token;

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);

		LOGGER.info("MemberID: {}", memberId);
		LOGGER.info("Policy ID: {}", policyId);

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("memberId", memberId);
		uriVariables.put("policyId", policyId);
		

		HttpEntity<MemberClaim> entity = new HttpEntity<MemberClaim>(claim, header);

		
		ResponseEntity<MemberClaim> exchange = restTemplate.exchange("http://localhost:9090/api/member-service/submitClaim/{policyId}/{memberId}", HttpMethod.POST, entity, MemberClaim.class, uriVariables);
		MemberClaim result = exchange.getBody();
		
		map.put("result", result);

		return "submitClaimStatus";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping(value = "/viewAllClaims")
	public String viewAllClaims(ModelMap map, HttpSession session) {

		int memberId = (int) session.getAttribute("userId");
		String token = (String) session.getAttribute("token");

		LOGGER.info("Token: {}", token);

		String jwtToken = "Bearer " + token;

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("memberId", memberId);

		HttpEntity<String> entity = new HttpEntity<String>(header);


		ResponseEntity<MemberClaim[]> exchange = restTemplate.exchange(
				"http://localhost:9090/api/member-service/getClaims/{memberId}", HttpMethod.GET, entity,
				MemberClaim[].class, uriVariables);
		MemberClaim[] result = exchange.getBody();

		map.put("claims", result);

		return "viewClaims";
	}

	@GetMapping(value = "/getClaimStatus/{policyNumber}/{claimNumber}")
	public String getClaimStatus(ModelMap map, HttpSession session, @PathVariable int policyNumber,
			@PathVariable int claimNumber) {

		int memberId = (int) session.getAttribute("userId");
		String token = (String) session.getAttribute("token");

		String jwtToken = "Bearer " + token;

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", jwtToken);

		HashMap<String, Integer> uriVariables = new HashMap<>();
		uriVariables.put("memberId", memberId);
		uriVariables.put("policyId", policyNumber);
		uriVariables.put("claimNumber", claimNumber);

		HttpEntity<String> entity = new HttpEntity<String>(header);

		ResponseEntity<MemberClaim> exchange = restTemplate.exchange("http://localhost:9090/api/member-service/getClaimStatus/{policyId}/{memberId}/{claimNumber}",
				HttpMethod.GET, entity, MemberClaim.class, uriVariables);
		MemberClaim result = exchange.getBody();

		map.put("claims", result);

		return "viewClaimsDetails";
	}

	@GetMapping(value = "/logout")
	public String test(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@PostMapping(value = "/login")
	public String successfulLogin(@RequestParam String username, @RequestParam String password, HttpSession session, ModelMap model) {
		LoginRequest request = new LoginRequest();
		request.setUsername(username);
		request.setPassword(password);
		try {
			
			ResponseEntity<LoginResponse> response = restTemplate.postForEntity(
					"http://localhost:9090/api/authentication-service/authenticate", request, LoginResponse.class);
			LoginResponse loginResponse = response.getBody();
			String token = loginResponse.getToken();
			User user = loginResponse.getUser();
			int userId = user.getId().intValue();

			session.setAttribute("userId", userId);
			session.setAttribute("token", token);
			return "dashboard";
			
		}
		catch (Exception e) {
			model.put("error", "Invalid Credentials");
			return "login";
		}



	}
	
	

}
