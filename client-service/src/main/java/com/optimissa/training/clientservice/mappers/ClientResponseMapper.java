package com.optimissa.training.clientservice.mappers;

import com.optimissa.training.clientservice.api.ClientResponse;
import com.optimissa.training.clientservice.model.Client;

public class ClientResponseMapper {

    public static ClientResponse mapToClientResponse(Client client){
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setName(client.getName());
        clientResponse.setLastName1(client.getLastName1());
        clientResponse.setLastName2(client.getLastName2());
        clientResponse.setEmail(client.getEmail());
        clientResponse.setPhone(client.getPhone());
        clientResponse.setIdentifier(client.getIdentifier());
        return clientResponse;
    }

}
