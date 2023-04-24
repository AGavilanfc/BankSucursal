package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.models.Iban;

public interface IibanRepository {

        public Iban getIban(int id);
    public Iban save(Iban iban);
    public void delete(Iban iban ,int id);

}
