package com.ust.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.banking.model.Account;
import com.ust.banking.model.Transaction;

import com.ust.banking.service.TransactionService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/transactions")
public class TransactionController {
	
	@Autowired
    private TransactionService transactionService;
	
	@GetMapping("/hello")
	public String Hello()
	{
		return "Hello";
	}
	
	@GetMapping(value="/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> getAll()
	{
		List<Transaction> result = transactionService.getAllTransactions();
		return ResponseEntity.ok().body(result);
	}

    @GetMapping("/{accountNo}")
    public List<Transaction> getTransactions(@PathVariable String accountNo) {
        return transactionService.getTransactions(accountNo);
    }

    @PostMapping(value="/transaction",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Transaction addTransaction(@RequestBody Transaction transaction) {

        return transactionService.addTransaction(transaction);
    }

    @GetMapping("balance/{accountNo}")
    public Account getAccountDetails(@PathVariable String accountNo) {

         return transactionService.getAccountDetails(accountNo);
    }

}
