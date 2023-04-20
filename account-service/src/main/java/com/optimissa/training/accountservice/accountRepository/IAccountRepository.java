package com.optimissa.training.accountservice.accountRepository;

import com.optimissa.training.accountservice.Models.Account;

public interface IAccountRepository {

    Account getAccount(int id);
}
