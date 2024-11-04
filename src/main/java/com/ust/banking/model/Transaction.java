package com.ust.banking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    @Column(name = "Amount")
    private Double amount;

    @Column(name = "Description")
    private String description;
    
    @Column(name = "Balance")
    private Double balance;
    
    @Column(name = "AccountNumber")
    private String accountNumber;

    @Column(name = "TransactionDate")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "TransactionType")
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "CategoryType")
    private CategoryType categoryType;

    @ManyToOne
    @JoinColumn(name = "aid", nullable = false)
    @JsonManagedReference
    @JsonIgnore
    private Account account;

    
    public Transaction(Double amount,String description, TransactionType transactionType, CategoryType categoryType, String accountNumber ) {
    	this.accountNumber = accountNumber;
        this.description = description;
        this.transactionType = transactionType;
        this.categoryType = categoryType;
        this.amount = amount;
    }
}
