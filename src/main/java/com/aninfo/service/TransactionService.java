package com.aninfo.service;


import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Transaction;
import com.aninfo.model.Account;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.TransactionRepository;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }
    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getType() == TransactionType.DEPOSIT) {
            accountService.deposit(transaction.getAccountCBU(), transaction.getAmount());
        }
        if (transaction.getType() == TransactionType.WITHDRAW) {
            accountService.withdraw(transaction.getAccountCBU(), transaction.getAmount());
        }
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> findByTransactionId(Long id) {
        return transactionRepository.findById(id);
    }

}
