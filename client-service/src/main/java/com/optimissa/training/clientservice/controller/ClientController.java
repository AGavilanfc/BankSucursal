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

    @GetMapping("")
    public List<Client> getClients() {
        logger.info("Call to getClients()");
        Long startTime = System.currentTimeMillis();
        List<Client> clients = service.getClients();
        Long endTime = System.currentTimeMillis();
        logger.info("Completed getClients() in {} ms Response: {}", (endTime - startTime), clients.toString());
        return clients;
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id){
        logger.info("Call to getClientById()");
        return service.getClientById(id);
    }

    @PostMapping("")
    public int insertClient(@RequestBody Client newClient) throws RuntimeException {
        return service.insertClient(newClient);
    }

    @DeleteMapping("/{id}")
    public int deleteClient(@PathVariable int id) {
        return service.deleteClient(id);
    }

    @PutMapping("/{id}")
    public int updateClient(@RequestBody Client modifiedClient, @PathVariable int id) {
        return service.updateClient(modifiedClient, id);
    }

}
