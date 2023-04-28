package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Iban;

public interface IibanRepository {

    Iban getIban(int id);

    void delete(Iban iban, int id);

    public int save(int countryId , int entityId, int branch, int accountControl, int accountNumber, int countryControl);

}
