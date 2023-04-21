package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Account;

public interface IAccountRepository {
    Account getAccount(int id);
}
