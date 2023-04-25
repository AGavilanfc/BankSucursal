package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Account;

import java.util.List;

public interface IAccountRepository {
    public Account getAccount(int id);
    public Account save(Account account);
    public int delete(int id);
    public List<Account> getAllAccount();

}
