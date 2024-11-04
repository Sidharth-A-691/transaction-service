package com.ust.banking.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.banking.model.Account;
import com.ust.banking.model.Transaction;
import com.ust.banking.model.TransactionType;
import com.ust.banking.repository.AccountRepository;
import com.ust.banking.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Transaction> getTransactions(String accountNo) {
        return transactionRepository.findAllByAccountNumber(accountNo);
    }
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction addTransaction(Transaction transaction) {
    	
    	Account account = accountRepository.findByAccountNumber(transaction.getAccountNumber());
    	if (account == null) {
    		throw new RuntimeException("Account not found with account number: " + transaction.getAccountNumber());
    	}
	    if (transaction.getTransactionType() == TransactionType.WITHDRAW) {
	        if (account.getAccountBalance() < transaction.getAmount()) {
	            throw new RuntimeException("Insufficient funds for withdrawal.");
	        }
	        account.setAccountBalance(account.getAccountBalance() - transaction.getAmount());
	    } else {
	        account.setAccountBalance(account.getAccountBalance() + transaction.getAmount());
	    }
	    
	    transaction.setBalance(account.getAccountBalance());
	    transaction.setTransactionDate(new Date());
	    transaction.setAccount(account); 

	    accountRepository.saveAndFlush(account);
	    return transactionRepository.save(transaction);

    }

    public Account getAccountDetails(String accountNumber) {

         return  accountRepository.findByAccountNumber(accountNumber);
    }
    
}
