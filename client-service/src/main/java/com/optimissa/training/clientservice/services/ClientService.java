package com.optimissa.training.clientservice.services;

import com.optimissa.training.clientservice.api.AccountRequest;
import com.optimissa.training.clientservice.api.ClientRequest;
import com.optimissa.training.clientservice.api.ClientResponse;
import com.optimissa.training.clientservice.controller.ClientController;
import com.optimissa.training.clientservice.mappers.ClientRequestMapper;
import com.optimissa.training.clientservice.mappers.ClientResponseMapper;
import com.optimissa.training.clientservice.model.Client;
import com.optimissa.training.clientservice.repository.IClientRepository;
import com.optimissa.training.clientservice.utils.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientService {

    Logger logger = LoggerFactory.getLogger(ClientController.class);


    private final IClientRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    public ClientService(IClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getClients() {
        return repository.getClients();
    }

    public ClientResponse getClientById(int id) {
        ClientResponse clientResponse = ClientResponseMapper.mapToClientResponse(repository.getClientById(id));
        return clientResponse;
    }

    public List<Client> getClientByUserId(int id) {
        return repository.getClientByUserId(id);
    }

    public int insertClient(Client newClient) throws RuntimeException {
        if(!ClientUtils.isValidEmail(newClient.getEmail())) {
            String msg = "Invalid email";
            logger.error("Error inserting client: {}", msg);
            throw new RuntimeException(msg);
        }
        return repository.insertClient(newClient);
    }

    public int deleteClient(int id) {
        return repository.deleteClient(id);
    }

    public int updateClient(ClientRequest clientToModify, int id) {
        Client modifiedClient = ClientRequestMapper.mapToClient(clientToModify);
        if(!ClientUtils.isValidEmail(modifiedClient.getEmail())) {
            String msg = "Invalid email";
            logger.error("Error inserting client: {}", msg);
            throw new RuntimeException(msg);
        }
        return repository.updateClient(modifiedClient, id);
    }

    public AccountRequest getAccount(String url) {
        return restTemplate.getForObject(url, AccountRequest.class);
    }

}
