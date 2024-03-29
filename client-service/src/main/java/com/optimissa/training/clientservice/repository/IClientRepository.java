package com.optimissa.training.clientservice.repository;

import com.optimissa.training.clientservice.model.Client;

import java.util.List;

public interface IClientRepository {

    public List<Client> getClients();

    public List<Client> selectByStartWith(String select, String data);


    public Client getClientById(int id);

    public Client getClientByIdentifier(String identifier);

    public Client getLastClient();

    public List<Client> getClientByUserId(int id);

    public int insertClient(Client newClient);

    public int deleteClient(int id);

    public int updateClient(Client modifiedClient, int id);


    List<Client> getUserBylimits(int limit, int page);
    public int getUserBylimitsCount();

    public int updateUserStatus(int id, int activate);
}
