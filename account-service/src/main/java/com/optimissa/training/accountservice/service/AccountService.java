package com.optimissa.training.accountservice.service;

import com.optimissa.training.accountservice.api.AccountRequest;
import com.optimissa.training.accountservice.mapper.AccountMapper;
import com.optimissa.training.accountservice.mapper.AccountRequestMapper;
import com.optimissa.training.accountservice.models.*;
import com.optimissa.training.accountservice.repository.AccountRepository;
import com.optimissa.training.accountservice.repository.EntityRepository;
import com.optimissa.training.accountservice.repository.IbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IbanRepository ibanRepository;

    @Autowired
    AccountRequestMapper accountRequestMapper;

    @Autowired
    ValidationIbanService validationIbanService;

    @Autowired
    AccountMapper accountMapper;


    public Account getAccount(int id) {
        return accountRepository.getAccount(id);
    }

    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }


    public void createAccount(AccountRequest accountRequest) {

        //mapper AccountRequest -> AccoutnDTO
        Account account = accountRequestMapper.maptoAccount(accountRequest);

        if(validationIbanService.validate(accountRequest.getIbanCountry(),accountRequest.getIbanEntity())){
            ibanRepository.save(accountRequest.getIbanCountry(),accountRequest.getIbanEntity());
            accountRepository.save(account);
        };

    }

    public int updateAccount(int id, Account account) {

        return accountRepository.update(account, id);
    }

    public StringResponse deleteAccount(int id) {
        String str = "a";


        if (accountRepository.delete(id) == 1) {
            str = "se ha desactivado la cuenta " + id;

        } else {
            str = "no se ha desactivado la cuenta " + id + " porque no existe";
        }

        return new StringResponse(str);

    }

    public Iban generateNewIban(AccountRequest accountRequest) {

        return new Iban();
    }

}
