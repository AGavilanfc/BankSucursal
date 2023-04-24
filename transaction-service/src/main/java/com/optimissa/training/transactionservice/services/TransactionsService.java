package com.optimissa.training.transactionservice.services;

import com.optimissa.training.transactionservice.dtos.Transactions;
import com.optimissa.training.transactionservice.repository.TransactionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    private TransactionRespository transactionRespository;

    public List<Transactions> getAllTransactions(){

       return transactionRespository.getAllTransactions();
    }

    public Transactions getByIdTransaction(int id) {

        return transactionRespository.getByIdTransaction(id);
    }

    public int insertNewTransaction(Transactions transaction) {
        return transactionRespository.insertNewTransaction(transaction);
    }
}
