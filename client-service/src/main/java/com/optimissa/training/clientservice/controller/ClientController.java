package com.optimissa.training.clientservice.controller;

import com.optimissa.training.clientservice.api.AccountRequest;
import com.optimissa.training.clientservice.api.ClientRequest;
import com.optimissa.training.clientservice.api.ClientResponse;
import com.optimissa.training.clientservice.model.Client;
import com.optimissa.training.clientservice.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getClientById(@PathVariable int id){
        logger.info("Searching client by id {}", id);
        Long startTime = System.currentTimeMillis();
        try {
            ClientResponse client = service.getClientById(id);
            Long endTime = System.currentTimeMillis();
            logger.info("Finished in {} ms Response: {}", (endTime - startTime), client.toString());
            return ResponseEntity.ok(client);
        } catch(Exception e) {
            logger.error("Error searching client with id {}: not found", id);
            return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> insertClient(@RequestBody Client newClient) throws RuntimeException {
        logger.info("Inserting new client {}", newClient);
        Long startTime = System.currentTimeMillis();
        try {
            int response = service.insertClient(newClient);
            Long endTime = System.currentTimeMillis();
            logger.info("Finished in {} ms Response: {}", (endTime - startTime), response);
        } catch (Exception e) {
            logger.error("Error inserting client: {}", e.getMessage());
            return ResponseEntity.unprocessableEntity().body("Incorrect data: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Client created");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable int id) {
        logger.info("Deleting client with id {}", id);
        Long startTime = System.currentTimeMillis();
        try {
            int response = service.deleteClient(id);
            if (response == 0) throw new Exception("id not found");
            Long endTime = System.currentTimeMillis();
            logger.info("Finished in {} ms Response: {}", (endTime - startTime), response);
        } catch (Exception e) {
            logger.error("Error deleting client with id {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body("Client terminated");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody ClientRequest modifiedClient, @PathVariable int id) {
        logger.info("Updating client with id {}", id);
        Long startTime = System.currentTimeMillis();
        try {
            int response = service.updateClient(modifiedClient, id);
            if (response == 0) {
                logger.error("Error updating client with id {}: not found", id);
                return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
            }
            Long endTime = System.currentTimeMillis();
            logger.info("Finished in {} ms Response: {}", (endTime - startTime), response);
        } catch (Exception e) {
            logger.error("Error updating client with id {}: {}", id, e.getMessage());
            return ResponseEntity.unprocessableEntity().body("Incorrect data: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Client updated");
    }


    //Communication with other modules

    @GetMapping("get-by-userId/{id}")
    public List<Client> getClientByUserId(@PathVariable int id){
        return service.getClientByUserId(id);
    }


    @GetMapping("/get-accounts/{id}")
    public AccountRequest getAccounts(@PathVariable int id) {
        String url = "https://119f818c-c2d2-4cec-b4a5-8163025854e0.mock.pstmn.io/get-by-clientId/" + id;
        return service.getAccount(url);
    }

}
