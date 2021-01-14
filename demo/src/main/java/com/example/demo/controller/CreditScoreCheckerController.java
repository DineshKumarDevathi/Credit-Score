package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.CreditScoreRequest;
import com.example.demo.pojo.CreditScoreResponse;
import com.example.demo.service.CreditScoreCheckerService;

@RestController
@RequestMapping("/credit/score")
public class CreditScoreCheckerController {

	@Autowired
	private CreditScoreCheckerService creditScoreCheckerService;
	
	@PostMapping(value="/checkcredit",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkCreditStatus(@RequestBody CreditScoreRequest request) {
		try {
		return ResponseEntity.ok(creditScoreCheckerService.getCreditScore(request));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e);
		}
	}
	@GetMapping("/getscore")
	public ResponseEntity<?> getMedia(){
		return ResponseEntity.ok("Welcome");
	}
}
