package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Account;

public interface IAccountRepository {
    public Account getAccount(int id);
    public Account save(Account account);
    public void delete(Account account ,int id);


}
