package com.optimissa.training.transactionservice.services;

import com.optimissa.training.transactionservice.dtos.Transactions;
import com.optimissa.training.transactionservice.repository.TransactionRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        String transaction_numbrer= "TRX"+new Date().getTime() + (int) (Math.random() * 1000);
        return transactionRespository.insertNewTransaction(transaction, transaction_numbrer);
        }


}
