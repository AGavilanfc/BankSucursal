package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Iban;

public interface IibanRepository {

    Iban getIban(int id);

    int save(int country , int entity);

    void delete(Iban iban, int id);

}
