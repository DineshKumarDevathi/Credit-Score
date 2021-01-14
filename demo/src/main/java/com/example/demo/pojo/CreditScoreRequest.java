package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreRequest {

	
	private String ssnNumber;
	private Double loanAmount;
	private Double annualIncome;
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
