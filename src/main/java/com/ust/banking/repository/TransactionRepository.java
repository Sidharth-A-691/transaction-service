package com.ust.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.banking.model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findAllByAccountNumber(String accountNumber);
}
