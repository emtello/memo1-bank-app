package com.aninfo.repository;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Transaction findTransactiontByTransactionId(Long id);

    List<Transaction> findTransactionByAccountCBU(Long accountCBU);
}