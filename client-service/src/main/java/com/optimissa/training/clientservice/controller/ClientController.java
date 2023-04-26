package com.optimissa.training.clientservice.controller;

import com.optimissa.training.clientservice.model.Client;
import com.optimissa.training.clientservice.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/get-all")
    public List<Client> getClients() {
        logger.info("Searching clients");
        Long startTime = System.currentTimeMillis();
        List<Client> clients = service.getClients();
        Long endTime = System.currentTimeMillis();
        logger.info("Finished in {} ms Response: {}", (endTime - startTime), clients.toString());
        return clients;
    }

    @GetMapping("get-by-id/{id}")
    public Client getClientById(@PathVariable int id){
        logger.info("Searching client by id {}", id);
        Long startTime = System.currentTimeMillis();
        try {
            Client client = service.getClientById(id);
            Long endTime = System.currentTimeMillis();
            logger.info("Finished in {} ms Response: {}", (endTime - startTime), client.toString());
            return client;
        } catch(Exception e) {
            logger.error("Error searching client with id {}: {}", id, e.getMessage());
        }
        return null;
    }

    @GetMapping("get-by-UserId/{id}")
    public List<Client> getClientByUserId(@PathVariable int id){
        return service.getClientByUserId(id);
    }


    @PostMapping("/create")
    public int insertClient(@RequestBody Client newClient) throws RuntimeException {
        logger.info("Inserting new client {}", newClient);
        Long startTime = System.currentTimeMillis();
        try {
            int response = service.insertClient(newClient);
            Long endTime = System.currentTimeMillis();
            logger.info("Finished in {} ms Response: {}", (endTime - startTime), response);
            return response;
        } catch(Exception e) {
            logger.error("Error inserting client: {}", e.getMessage());
        }
        return 0;
    }

    @DeleteMapping("delete/{id}")
    public int deleteClient(@PathVariable int id) {
        logger.info("Deleting client with id {}", id);
        Long startTime = System.currentTimeMillis();
        try {
            int response = service.deleteClient(id);
            if (response == 0) throw new Exception("id not found");
            Long endTime = System.currentTimeMillis();
            logger.info("Finished in {} ms Response: {}", (endTime - startTime), response);
            return response;
        } catch (Exception e) {
            logger.error("Error deleting client with id {}: {}", id, e.getMessage());
        }
        return 0;
    }

    @PutMapping("update/{id}")
    public int updateClient(@RequestBody Client modifiedClient, @PathVariable int id) {
        logger.info("Updating client with id {}", id);
        Long startTime = System.currentTimeMillis();
        try {
            int response = service.updateClient(modifiedClient, id);
            if (response == 0) throw new Exception("id not found");
            Long endTime = System.currentTimeMillis();
            logger.info("Finished in {} ms Response: {}", (endTime - startTime), response);
            return response;
        } catch (Exception e) {
            logger.error("Error updating client with id {}: {}", id, e.getMessage());
        }
        return 0;
    }

}
