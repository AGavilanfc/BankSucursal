package com.optimissa.training.transactionservice.repository;

import com.optimissa.training.transactionservice.dtos.Transactions;

import java.util.List;

public interface ITransactionRepository {
    public List<Transactions> getAllTransactions();



    public Transactions getByIdTransaction(int id);
}
