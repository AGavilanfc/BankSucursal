package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Account;

import java.util.List;
import java.util.Map;

public interface IAccountRepository {
    public Account getAccount(int id);
    public int save(Account account);
    public int delete(int id);
    public List<Account> getAllAccount();
    List<Account> getAccountByClient(int clientId);
    int updateAddBalance(int id, double balance);
    int updateBalanceSubstract(int id, double balance);
    int detIBANEntity(int ibanEntityId);

    String getibanCountry(int ibanCountryId);

    int modifyAccount(Account account, int id);

    int updateAccountRol(int id, String rol);

    int updateAccountStatus(int id, int active);
}
