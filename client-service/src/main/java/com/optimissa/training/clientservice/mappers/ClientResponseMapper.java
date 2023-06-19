package com.optimissa.training.clientservice.mappers;

import com.optimissa.training.clientservice.api.ClientResponse;
import com.optimissa.training.clientservice.model.Client;

public class ClientResponseMapper {

    public static ClientResponse mapToClientResponse(Client client){
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setName(client.getName());
        clientResponse.setLastName1(client.getLastName1());
        clientResponse.setLastName2(client.getLastName2());
        clientResponse.setEmail(client.getEmail());
        clientResponse.setPhone(client.getPhone());
        clientResponse.setActive(client.isActive());
        clientResponse.setIdentifier(client.getIdentifier());
        clientResponse.setUser_id(client.getUserId());
        return clientResponse;
    }

}
