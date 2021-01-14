package com.example.demo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Credit_Score_Checker")
@Data
public class CreditScoreCheckerEO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String ssnNumber;
	private Double loanAmount;
	private Double annualIncome;
	private Date time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	} 
		
}
