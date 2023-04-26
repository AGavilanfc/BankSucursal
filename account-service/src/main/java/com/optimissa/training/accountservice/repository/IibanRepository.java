package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Iban;

public interface IibanRepository {

    Iban getIban(int id);

    Iban save(Iban iban);

    void delete(Iban iban, int id);

}
