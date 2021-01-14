package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CreditScoreCheckerEO;

@Repository
public interface CreditScoreCheckerDao extends JpaRepository<CreditScoreCheckerEO, Integer> {

	public CreditScoreCheckerEO findBySsnNumber(String ssnNumber);
	
}
