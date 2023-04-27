package com.optimissa.training.clientservice.mappers;

import com.optimissa.training.clientservice.api.AccountRequest;
import com.optimissa.training.clientservice.api.AccountResponse;
import com.optimissa.training.clientservice.api.CurrencyResponse;

public class AccountRequestMapper {

    public static AccountResponse mapToAccountResponse(AccountRequest accountRequest, CurrencyResponse currencyResponse) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setIban(accountRequest.getIban());
        accountResponse.setBalance(accountRequest.getBalance());
        accountResponse.setCurrency(currencyResponse.getCode());
        return accountResponse;
    }
}
