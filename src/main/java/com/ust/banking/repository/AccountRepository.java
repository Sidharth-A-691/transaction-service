package com.ust.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.banking.model.Account;


public interface AccountRepository extends JpaRepository<Account,Long> {
	 
	 Account findByAccountNumber(String accountNumber);
}
