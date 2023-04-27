package com.optimissa.training.accountservice.mapper;

import com.optimissa.training.accountservice.api.AccountRequest;
import com.optimissa.training.accountservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestMapper {

    public Account maptoAccount(AccountRequest accountRequest){
        Account account = new Account();
        account.setClient_id(accountRequest.getClientId());
        account.setCurrency_id(accountRequest.getCurrencyId());
        return account;
    }
}
