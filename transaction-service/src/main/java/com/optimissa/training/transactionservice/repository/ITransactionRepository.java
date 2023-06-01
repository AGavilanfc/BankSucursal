package com.optimissa.training.transactionservice.repository;

import com.optimissa.training.transactionservice.dtos.Account;
import com.optimissa.training.transactionservice.dtos.Transaction;

import java.util.List;

public interface ITransactionRepository {
    public List<Transaction> getAllTransactions(int limit, int page);



    public Transaction getByIdTransaction(int id);



    public int insertNewTransaction(Transaction transaction, String transaction_number);

    List<Account> getAccountBylimits(int id, int page, int limit);

    int getAccountBylimitsCount();
}
