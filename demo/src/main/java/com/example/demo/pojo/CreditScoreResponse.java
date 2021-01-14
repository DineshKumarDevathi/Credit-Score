package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreResponse {
	
	private String status;
	private String applicationErrorMessage;
	private String ssnNumber;
	private Double loanAmount;
	private Double annualIncome;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplicationErrorMessage() {
		return applicationErrorMessage;
	}
	public void setApplicationErrorMessage(String applicationErrorMessage) {
		this.applicationErrorMessage = applicationErrorMessage;
	}
	public String getSsnNumber() {
		return ssnNumber;
	}
	public void setSsnNumber(String ssnNumber) {
		this.ssnNumber = ssnNumber;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}
	

}
