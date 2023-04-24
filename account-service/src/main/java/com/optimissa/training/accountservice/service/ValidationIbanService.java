package com.optimissa.training.accountservice.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationIbanService {

    public boolean validate(int ibanId) {

        String ibanid= String.valueOf(ibanId);

        //logica

        return true;
    }


}
