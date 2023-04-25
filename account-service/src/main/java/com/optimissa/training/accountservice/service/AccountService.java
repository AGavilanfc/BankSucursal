package com.optimissa.training.accountservice.service;
import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.repository.AccountRepository;
import com.optimissa.training.accountservice.repository.IbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IbanRepository ibanRepository;



    public Account getAccount(int id){

        return accountRepository.getAccount(id);
    }


    public Account createAccount(Account account ){

        return accountRepository.save(account);
    }

    public Account updateAccount(int id,Account account){
        Account accountExisting = accountRepository.getAccount(id);
        accountExisting.setId(account.getId());
        accountExisting.setBalance(account.getBalance());
        accountExisting.setIban_id(account.getIban_id());
        accountExisting.setCurrency_id(account.getCurrency_id());
        accountExisting.setActive(account.isActive());


        return accountRepository.save(accountExisting);
    }

    public void deleteAccount(int id){
        Account accountExisting = accountRepository.getAccount(id);
        accountRepository.delete(accountExisting,id);

    }



}
