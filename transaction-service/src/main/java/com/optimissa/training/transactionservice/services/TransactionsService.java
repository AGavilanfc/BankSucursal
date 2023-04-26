package com.optimissa.training.transactionservice.services;

import com.optimissa.training.transactionservice.api.AccountResponse;
import com.optimissa.training.transactionservice.api.FundResponse;
import com.optimissa.training.transactionservice.api.TransactionResponse;
import com.optimissa.training.transactionservice.dtos.Transaction;
import com.optimissa.training.transactionservice.repository.TransactionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class TransactionsService {
    RestTemplate restTemplate = new RestTemplate();
    String urlFund = "http://localhost:8095/funds";
    String urlAccount = "http://localhost:8092/accounts/getAccount";
    String urlCurrency = "http://localhost:8093/currencies";


    @Autowired
    private TransactionRespository transactionRespository;

    public List<Transaction> getAllTransactions() {
        return transactionRespository.getAllTransactions();
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


}
