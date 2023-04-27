package com.optimissa.training.clientservice.mappers;

import com.optimissa.training.clientservice.api.ClientRequest;
import com.optimissa.training.clientservice.model.Client;

public class ClientRequestMapper {

    public static Client mapToClient(ClientRequest requestClient){
        Client client = new Client();
        client.setName(requestClient.getName());
        client.setLastName1(requestClient.getLastName1());
        client.setLastName2(requestClient.getLastName2());
        client.setEmail(requestClient.getEmail());
        client.setPhone(requestClient.getPhone());
        client.setUserId(requestClient.getUserId());
        return client;
    }

}
