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
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public ClientResponse getClientByIdentifier(String identifier){
        Client client = repository.getClientByIdentifier(identifier);
        return ClientResponseMapper.mapToClientResponse(client);
    }

    public ClientResponse getLastClient() {
        Client client = repository.getLastClient();
        return ClientResponseMapper.mapToClientResponse(client);
    }

    public List<Client> getClientByStartWith(String select, String data) {
        long startTime = System.currentTimeMillis();
        List<Client> user = repository.selectByStartWith(select, data);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUserById(). Execution took: {}ms. Response: {}", endTime-startTime, user.toString() );
        return user;
    }

    public int insertClient(ClientRequest newClientRequest) throws RuntimeException {
        Client newClient = ClientRequestMapper.mapToClient(newClientRequest);
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
        AccountRequest[] accountsRequest = restTemplate.getForObject(url + id, AccountRequest[].class);
        List<AccountResponse> accountsResponse = new ArrayList<>();
        for(AccountRequest account : accountsRequest) {
            CurrencyResponse currencyResponse = getCurrencyResponse(account.getCurrency_id());
            accountsResponse.add(AccountRequestMapper.mapToAccountResponse(account, currencyResponse));
        }
        return accountsResponse;
    }


    public Object getUserBylimits(int limit, int page) {
        long startTime = System.currentTimeMillis();
        List<Client> users = repository.getUserBylimits(limit, page);
        int totalElements = repository.getUserBylimitsCount();
        int totalPages = (int) Math.ceil((double) totalElements / 10);

        long endTime = System.currentTimeMillis();


        Map<String, Object> result = new HashMap<>();
        result.put("totalPages", totalPages);
        result.put("users", users);
        logger.info("Finished userService.getUserById(). Execution took: {}ms. Response: {}", endTime-startTime, users.toString() );
        return result;
    }

    public int updateUserStatus(int id, int activate) {
        logger.info("Started userService.updateUser()");
        long startTime = System.currentTimeMillis();
        int affectedRows = repository.updateUserStatus(id,activate);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.updateUser(). Execution took: {}ms. Response: affectedRows = {}", endTime-startTime, affectedRows );
        return affectedRows;
    }
}
