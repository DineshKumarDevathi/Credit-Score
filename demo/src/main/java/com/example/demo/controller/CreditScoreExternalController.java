package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.CreditScoreRequest;
import com.example.demo.service.CreditScoreCheckerService;
import com.example.demo.service.CreditScoreExternalService;

import org.slf4j.Logger;
import 	org.slf4j.Logger.*;

@RestController
@RequestMapping("/external/credit")
public class CreditScoreExternalController {
 Logger logger = org.slf4j.LoggerFactory.getLogger(CreditScoreExternalController.class);
	@Autowired
	private CreditScoreExternalService creditScoreExternalService;
	@PostMapping(value="/getscore",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> validateCreditScore(@RequestBody CreditScoreRequest request){
		logger.info("Inside External System Credit Controller");
		try {
		return ResponseEntity.ok(creditScoreExternalService.evaluateCreditScore(request));
	}
        catch(Exception e) {
        	return ResponseEntity.ok(e);
	}
}
}
