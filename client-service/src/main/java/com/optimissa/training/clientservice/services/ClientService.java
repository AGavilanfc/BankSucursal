package com.optimissa.training.clientservice.services;

import com.optimissa.training.clientservice.model.Client;
import com.optimissa.training.clientservice.repository.IClientRepository;
import com.optimissa.training.clientservice.utils.ClientUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final IClientRepository repository;

    public ClientService(IClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getClients() {
        return repository.getClients();
    }

    public Client getClientById(int id) {
        return repository.getClientById(id);
    }

    public List<Client> getClientByUserId(int id) {
        return repository.getClientByUserId(id);
    }

    public int insertClient(Client newClient) throws RuntimeException {
        if(!ClientUtils.isValidEmail(newClient.getEmail())) throw new RuntimeException("Invalid email");
        return repository.insertClient(newClient);
    }

    public int deleteClient(int id) {
        return repository.deleteClient(id);
    }

    public int updateClient(Client modifiedClient, int id) {
        return repository.updateClient(modifiedClient, id);
    }

}
