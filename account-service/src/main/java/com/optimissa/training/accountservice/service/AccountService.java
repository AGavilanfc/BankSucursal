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
import java.util.Map;
import java.util.Random;


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


    public List<Account> getAccountByClient(int clientId) {
        return accountRepository.getAccountByClient(clientId);
    }

    public boolean createAccount(AccountRequest accountRequest) {

        int ibanCountry = accountRequest.getIbanCountry();
        int ibanEntity = accountRequest.getIbanEntity();

        //mapper AccountRequest -> AccountDTO
        Account account = accountRequestMapper.maptoAccount(accountRequest);
        int lineaAccount = 0;

        if(validationIbanService.validate(ibanCountry, ibanEntity)){

            account.setIban(generateIBAN(ibanEntity, ibanCountry, account));
            lineaAccount = accountRepository.save(account);
        }
        return lineaAccount > 0;
    }

    private String generateIBAN(int ibanEntity, int ibanCountry, Account a) {

        int max = 999_999_999;
        int min = 100_000_000;
        Random random = new Random();
        int countryControl = random.nextInt(100);
        int branch = random.nextInt(9000) + 1000;
        int accountControl = random.nextInt(100);
        int accountNumber = random.nextInt((max - min) + 1) + min;
        int lastNumber = random.nextInt(10);

        int iban = ibanRepository.save(ibanCountry, ibanEntity, branch, accountControl, accountNumber, countryControl);
        a.setIban_id(iban);

        String country = accountRepository.getibanCountry(ibanCountry);
        int entity = accountRepository.detIBANEntity(ibanEntity);

        String result = country+countryControl+entity+branch+accountControl+accountNumber+lastNumber;

        return result;
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
