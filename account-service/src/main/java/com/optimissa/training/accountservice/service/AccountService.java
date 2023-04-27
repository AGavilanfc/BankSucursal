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
        Account acc = accountRepository.getAccount(id);

        return  new Account();
    }

    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }


    public List<Account> getAccountByClient(int clientId) {
        return accountRepository.getAccountByClient(clientId);
    }

    public boolean createAccount(AccountRequest accountRequest) {

        //mapper AccountRequest -> AccoutnDTO
        Account account = accountRequestMapper.maptoAccount(accountRequest);
        int lineaAccount = 0;
        if(validationIbanService.validate(accountRequest.getIbanCountry(),accountRequest.getIbanEntity())){
            int iban = ibanRepository.save(accountRequest.getIbanCountry(),accountRequest.getIbanEntity());
            account.setIban_id(iban);
            lineaAccount = accountRepository.save(account);
        }
        return lineaAccount > 0;
    }

    public int updateBalance(int id, double balance) {
        return accountRepository.updateAddBalance(id,balance);
    }

//    public boolean updateBalanceDeduct(int id, double balance) {
//        return accountRepository.updateBalance(id,balance);
//    }

    public StringResponse deleteAccount(int id) {
        String str = "a";


        if (accountRepository.delete(id) == 1) {
            str = "se ha desactivado la cuenta " + id;

        } else {
            str = "no se ha desactivado la cuenta " + id + " porque no existe";
        }

        return new StringResponse(str);
    }

}
