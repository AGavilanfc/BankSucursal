package com.optimissa.training.accountservice.Service;

import com.optimissa.training.accountservice.Models.Account;
import com.optimissa.training.accountservice.accountRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount(int id){
        return accountRepository.getAccount(id);
    }
}
