package com.optimissa.training.clientservice.services;

import com.optimissa.training.clientservice.api.*;
import com.optimissa.training.clientservice.controller.ClientController;
import com.optimissa.training.clientservice.mappers.AccountRequestMapper;
import com.optimissa.training.clientservice.mappers.ClientRequestMapper;
import com.optimissa.training.clientservice.mappers.ClientResponseMapper;
import com.optimissa.training.clientservice.mappers.CurrencyRequestMapper;
import com.optimissa.training.clientservice.model.Client;
import com.optimissa.training.clientservice.repository.IClientRepository;
import com.optimissa.training.clientservice.utils.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final IClientRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    Logger logger = LoggerFactory.getLogger(ClientController.class);

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


    public int insertClient(Client newClient) throws RuntimeException {
        if (!ClientUtils.isValidEmail(newClient.getEmail())) {
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
        if (!ClientUtils.isValidEmail(modifiedClient.getEmail())) {
            String msg = "Invalid email";
            logger.error("Error inserting client: {}", msg);
            throw new RuntimeException(msg);
        }
        return repository.updateClient(modifiedClient, id);
    }

    //Communication with other services

    public List<ClientResponse> getClientByUserId(int id) {
        List<Client> clients = repository.getClientByUserId(id);
        List<ClientResponse> clientResponses = new ArrayList<>();
        for (Client client : clients) {
            clientResponses.add(ClientResponseMapper.mapToClientResponse(client));
        }
        return clientResponses;
    }

    public CurrencyResponse getCurrencyResponse(int currencyId) {
        String url = "http://localhost:8093/currencies/get-by-id/";
        CurrencyRequest currencyRequest = restTemplate.getForObject(url + currencyId, CurrencyRequest.class);
        return CurrencyRequestMapper.mapToCurrencyResponse(currencyRequest);
    }

    public List<AccountResponse> getAccountsByClientId(int id) {
        String url = "http://localhost:8092/accounts/get-by-client/";
        List<AccountRequest> accountsRequest = restTemplate.getForObject(url + id, List.class);
        List<AccountResponse> accountsResponse = new ArrayList<>();
        for(AccountRequest account : accountsRequest) {
            CurrencyResponse currencyResponse = getCurrencyResponse(account.getCurrencyId());
            accountsResponse.add(AccountRequestMapper.mapToAccountResponse(account, currencyResponse));
        }
        return accountsResponse;
    }

}
