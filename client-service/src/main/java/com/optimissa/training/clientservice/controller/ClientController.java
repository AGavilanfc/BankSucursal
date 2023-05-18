package com.optimissa.training.clientservice.controller;

import com.optimissa.training.clientservice.api.*;
import com.optimissa.training.clientservice.model.Client;
import com.optimissa.training.clientservice.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getClients() {
        logger.info("Searching clients");
        Long startTime = System.currentTimeMillis();
       try {
           List<Client> clients = service.getClients();
           Long endTime = System.currentTimeMillis();
           logger.info("Finished getClients in {} ms", (endTime - startTime));
           return ResponseEntity.ok(clients);
       } catch (Exception e) {
           logger.error("Error searching clients: {}", e.getMessage());
           return new ResponseEntity<>("Clients not found. ", HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable int id){
        logger.info("Searching client by id {}", id);
        Long startTime = System.currentTimeMillis();
        try {
            ClientResponse client = service.getClientById(id);
            Long endTime = System.currentTimeMillis();
            logger.info("Finished getClientById in {} ms", (endTime - startTime));
            return ResponseEntity.ok(client);
        } catch(Exception e) {
            logger.error("Error searching client with id {}: not found", id);
            return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-by-identifier/{identifier}")
    public ResponseEntity<Object> getClientsByIdentifier(@PathVariable String identifier) {
        logger.info("Searching client by identifier {}", identifier);
        Long startTime = System.currentTimeMillis();
        try {
            ClientResponse client = service.getClientByIdentifier(identifier);
            Long endTime = System.currentTimeMillis();
            logger.info("Finished getClientByIdentifier in {} ms", (endTime - startTime));
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            logger.error("Error searching client with id {}: not found", identifier);
            return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-last")
    public ResponseEntity<Object> getLastClient() {
        logger.info("Searching last client");
        Long startTime = System.currentTimeMillis();
        try {
            ClientResponse client = service.getLastClient();
            Long endTime = System.currentTimeMillis();
            logger.info("Finished getLastClient in {} ms", (endTime - startTime));
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            logger.error("Error searching last client: not found");
            return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-StartWith/{select}/{data}")
    public ResponseEntity<Object> getUserByStartWith(@PathVariable String select, @PathVariable String data) {
        try {
            return new ResponseEntity<>(service.getClientByStartWith(select,data), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Not found");
            return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-clients/{page}")
    public ResponseEntity<Object> getUserBylimits(@PathVariable int page) {
        try {
            return new ResponseEntity<>(service.getUserBylimits(10,page), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Not found");
            return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> insertClient(@RequestBody ClientRequest newClient) throws RuntimeException {
        logger.info("Inserting new client {}", newClient);
        Long startTime = System.currentTimeMillis();
        try {
            int response = service.insertClient(newClient);
            Long endTime = System.currentTimeMillis();
            logger.info("Finished insertClient in {} ms", (endTime - startTime));
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
            logger.info("Finished deleteClient in {} ms", (endTime - startTime));
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
            logger.info("Finished updateClient in {} ms", (endTime - startTime));
        } catch (Exception e) {
            logger.error("Error updating client with id {}: {}", id, e.getMessage());
            return ResponseEntity.unprocessableEntity().body("Incorrect data: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Client updated");
    }


    //Communication with other modules

    @GetMapping("get-by-userId/{id}")
    public ResponseEntity<Object> getClientByUserId(@PathVariable int id){
        logger.info("Searching clients by userId {}", id);
        Long startTime = System.currentTimeMillis();
        try {
            List<ClientResponse> clients = service.getClientByUserId(id);
            if (clients.isEmpty()) throw new Exception("not found");
            Long endTime = System.currentTimeMillis();
            logger.info("Finished getClientsByUserId in {} ms", (endTime - startTime));
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            logger.error("Error searching clients with userId {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Clients not found. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-accounts/by-client-id/{id}")
    public List<AccountResponse> getAccounts(@PathVariable int id) {
        return service.getAccountsByClientId(id);
    }

}
