package com.example.demo.demoservice;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.CreditScoreRequest;
import com.example.demo.pojo.CreditScoreResponse;
import com.example.demo.service.CreditScoreCheckerService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(MockitoJUnitRunner.class)
public class DemoTestService {
	  @Mock
	    private RestTemplate restTemplate;

	    @InjectMocks
	    private CreditScoreCheckerService empService = new CreditScoreCheckerService();
	    @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	    @Test
	    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
	     CreditScoreRequest request=new CreditScoreRequest();
	     request.setAnnualIncome(1800000.00);
	     request.setLoanAmount(800000.00);
	     request.setSsnNumber("SS1234333");
	     CreditScoreResponse response = new CreditScoreResponse();
	     response.setLoanAmount(1800000.00/2);
	     response.setLoanAmount(800000.00);
	     response.setSsnNumber("SS1234333");
	        Mockito
	          .when(restTemplate.postForEntity("http://localhost:8081/external/credit", request, CreditScoreResponse.class)
	           )
	          .thenReturn(new ResponseEntity(response, HttpStatus.OK));

	        Assert.assertNotNull(response);
	    }
}
