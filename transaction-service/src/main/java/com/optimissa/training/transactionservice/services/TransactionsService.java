package com.optimissa.training.transactionservice.services;

import com.optimissa.training.transactionservice.api.*;
import com.optimissa.training.transactionservice.controllers.TransactionsController;
import com.optimissa.training.transactionservice.dtos.Transaction;
import com.optimissa.training.transactionservice.repository.TransactionRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class TransactionsService {
    private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);

    RestTemplate restTemplate = new RestTemplate();
    String urlFund = "http://localhost:8095/funds/get-by-id";
    String urlAccount = "http://localhost:8092/accounts/get-by-id";
    String urlAccount2 = "http://localhost:8092/accounts";



    @Autowired
    private TransactionRespository transactionRespository;

    public List<Transaction> getAllTransactions(int limit, int page) {
        return transactionRespository.getAllTransactions(limit, page);
    }

    public List<Transaction> getAllTransactionsByAmount(int min, int max) {
        return transactionRespository.getAllTransactionsByAmount(min,max);
    }


    public TransactionResponse getByIdTransaction(int id) {

        Transaction transaction = transactionRespository.getByIdTransaction(id);
        AccountResponse account = getByIdAccount(transaction.getAccount_Id());
        FundResponse fund = getByIdFund(transaction.getFund_Id());
        return new TransactionResponse(
                transaction.getDate(),
                transaction.getAmount(),
                account,
                fund

        );
    }

    public AccountResponse getByIdAccount(int id) {
        return restTemplate.getForObject(urlAccount + "/" + id, AccountResponse.class);
    }

    public FundResponse getByIdFund(int id) {
        return restTemplate.getForObject(urlFund + "/" + id, FundResponse.class);
    }


    public int insertNewTransaction(Transaction transaction) {

        updateAccount(transaction.getAccount_Id(), transaction.getAmount());
        String transaction_numbrer = "TRX" + new Date().getTime() + (int) (Math.random() * 1000);

        return transactionRespository.insertNewTransaction(transaction, transaction_numbrer);
    }

    public int updateAccount(int id_account, double amount) {

       AccountResponse account = getByIdAccount(id_account);
       account.getBalance();

        if (account.getBalance() > 0) {
            restTemplate.put(urlAccount2 + "/update/to-account/" + id_account + "/add-balance/" + amount, Object.class);
        } else{
            restTemplate.put(urlAccount2 + "/update/to-account/" + id_account + "/substract-balance/" + amount * -1,  Object.class);
        }
        return 1;
    }

}
