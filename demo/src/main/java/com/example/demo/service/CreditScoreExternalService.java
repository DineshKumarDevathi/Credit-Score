package com.example.demo.service;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojo.CreditScoreRequest;
import com.example.demo.pojo.CreditScoreResponse;


@Service
@Transactional
public class CreditScoreExternalService {
  
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CreditScoreExternalService.class);
	
	public CreditScoreResponse evaluateCreditScore(CreditScoreRequest request) {
		logger.info("evaluating credit score from external systems");
    int score=StringUtils.isNotBlank(request.getSsnNumber())&&StringUtils.startsWith(request.getSsnNumber(),"SS")?750:650;
    CreditScoreResponse response = new CreditScoreResponse();
    response.setSsnNumber(request.getSsnNumber());
   // response.setApplicationErrorMessage(score<700?"NOT ELIGIBLE FOR THE LOAN":"ELIGIBLE FOR THE LOAN");
    response.setStatus(score<700?"PLEASE IMPROVE CREDIT SCORE":"ELIGIBLE FOR THE LOAN");
    if(score>700) {
    	response.setLoanAmount(request.getAnnualIncome()/2);
    	
    }else {
    	response.setLoanAmount(request.getAnnualIncome());
    }
    return response;
	}
}
