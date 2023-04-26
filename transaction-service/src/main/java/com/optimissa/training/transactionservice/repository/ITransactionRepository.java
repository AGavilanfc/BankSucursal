package com.optimissa.training.transactionservice.repository;

import com.optimissa.training.transactionservice.dtos.Transaction;

import java.util.List;

public interface ITransactionRepository {
    public List<Transaction> getAllTransactions();



    public Transaction getByIdTransaction(int id);



    public int insertNewTransaction(Transaction transaction, String transaction_number);
}
