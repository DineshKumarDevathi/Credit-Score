package com.example.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.CreditScoreCheckerDao;
import com.example.demo.model.CreditScoreCheckerEO;
import com.example.demo.pojo.CreditScoreRequest;
import com.example.demo.pojo.CreditScoreResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class CreditScoreCheckerService {
   Logger logger =LoggerFactory.getLogger(CreditScoreCheckerService.class);
	@Autowired
	private CreditScoreCheckerDao creditScoreCheckerDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${resource.url}")
	private String resourceUrl;

	public CreditScoreResponse getCreditScore(CreditScoreRequest request)throws Exception {
		ResponseEntity<CreditScoreResponse> responseEntity = null;
		logger.info(new ObjectMapper().writeValueAsString(request));
		CreditScoreCheckerEO checkerEO = creditScoreCheckerDao.findBySsnNumber(request.getSsnNumber());
		if (Objects.isNull(checkerEO)) {
			CreditScoreCheckerEO checkEO = new CreditScoreCheckerEO();
			checkEO.setAnnualIncome(request.getAnnualIncome());
			checkEO.setLoanAmount(request.getLoanAmount());
			checkEO.setSsnNumber(request.getSsnNumber());
			checkEO.setTime(new Date());
			creditScoreCheckerDao.save(checkEO);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CreditScoreRequest> httpEntity = new HttpEntity<CreditScoreRequest>(request,headers);
			responseEntity = restTemplate.exchange(resourceUrl+"/getscore", HttpMethod.POST, httpEntity, CreditScoreResponse.class);
			return responseEntity.getBody();
		} else {
			LocalDate strtDate = checkerEO.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate endDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int days = Period.between(strtDate, endDate).getDays();
			if (days > 30) {
				checkerEO.setTime(new Date());
				creditScoreCheckerDao.save(checkerEO);
				HttpEntity<CreditScoreRequest> httpEntity = new HttpEntity<CreditScoreRequest>(request);
				responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.POST, httpEntity,
						CreditScoreResponse.class);
				return responseEntity.getBody();
			} else {
				CreditScoreResponse response = new CreditScoreResponse();
				response.setSsnNumber(request.getSsnNumber());
				response.setApplicationErrorMessage("You cannot check credit score before 30 days");
				response.setAnnualIncome(request.getAnnualIncome());
				response.setLoanAmount(request.getLoanAmount());
				return response;
			}
		}

	}

}
