package com.optimissa.training.clientservice.repository;

import com.optimissa.training.clientservice.model.Client;

import java.util.List;

public interface IClientRepository {

    public List<Client> getClients();

    public Client getClientById(int id);

    public Client getClientByIdentifier(String identifier);

    public List<Client> getClientByUserId(int id);

    public int insertClient(Client newClient);

    public int deleteClient(int id);

    public int updateClient(Client modifiedClient, int id);

}
