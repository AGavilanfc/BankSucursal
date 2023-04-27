package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Account;

import java.util.List;

public interface IAccountRepository {
    public Account getAccount(int id);
    public int save(Account account);
    public int delete(int id);
    public List<Account> getAllAccount();
    public int update(Account account , int id);

    List<Account> getAccountByClient(int clientId);
}
