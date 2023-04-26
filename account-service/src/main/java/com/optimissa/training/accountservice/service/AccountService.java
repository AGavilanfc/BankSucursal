package com.optimissa.training.accountservice.service;

import com.optimissa.training.accountservice.api.AccountRequest;
import com.optimissa.training.accountservice.mapper.AccountRequestMapper;
import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.models.Iban;
import com.optimissa.training.accountservice.models.StringResponse;
import com.optimissa.training.accountservice.repository.AccountRepository;
import com.optimissa.training.accountservice.repository.IbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountRequestMapper accountRequestMapper;

    @Autowired
    ValidationIbanService validationIbanService;


    public Account getAccount(int id) {
        return accountRepository.getAccount(id);
    }

    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }


    public int createAccount(AccountRequest accountRequest) {

        //mapper AccountRequest -> AccoutnDTO
        Account account = accountRequestMapper.maptoAccount(accountRequest);

        //validar IBAN
        if(validationIbanService.validate(account.getIban_id());
        // crear iban


        //crear un nuevo iban, 3 datos de la request


        return accountRepository.save(account);
    }

    public int updateAccount(int id, Account account) {
//        Account accountExisting = accountRepository.getAccount(id);
//        accountExisting.setId(account.getId());
//        accountExisting.setBalance(account.getBalance());
//        accountExisting.setIban_id(account.getIban_id());
//        accountExisting.setCurrency_id(account.getCurrency_id());
//        accountExisting.setActive(account.isActive());


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
