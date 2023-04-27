package com.optimissa.training.transactionservice.services;

import com.optimissa.training.transactionservice.api.AccountResponse;
import com.optimissa.training.transactionservice.api.FundResponse;
import com.optimissa.training.transactionservice.api.TransactionRequest;
import com.optimissa.training.transactionservice.api.TransactionResponse;
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
    String urlFund = "http://localhost:8095/funds";
    String urlAccount = "http://localhost:8092/accounts/getAccount";
    String urlCurrency = "http://localhost:8093/currencies";




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
        return new TransactionResponse(
                transaction.getDate(),
                transaction.getAmount(),
                getByIdAccount(transaction.getAccount_Id()),
                getByIdFund(transaction.getFund_Id())
        );
    }

    public AccountResponse getByIdAccount(int id) {
        return restTemplate.getForObject(urlAccount + "/" + id, AccountResponse.class);
    }

    public FundResponse getByIdFund(int id) {
        return restTemplate.getForObject(urlFund + "/id/" + id, FundResponse.class);
    }


    public int insertNewTransaction(Transaction transaction) {
        String transaction_numbrer = "TRX" + new Date().getTime() + (int) (Math.random() * 1000);
        return transactionRespository.insertNewTransaction(transaction, transaction_numbrer);
    }

    public int updateAccount(int id_transaction,int id_account, int balance) {
        //{{USER-ACCOUNT}}/accounts/update/to-account/15/add-balance/220.0
        //{{USER-ACCOUNT}}/accounts/update/to-account/15/substract-balance/220.0

    return 0;



    }


}
