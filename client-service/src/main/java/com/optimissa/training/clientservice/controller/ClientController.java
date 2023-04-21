package com.optimissa.training.clientservice.controller;

import com.optimissa.training.clientservice.model.Client;
import com.optimissa.training.clientservice.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Client> getClients() {
        return service.getClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id){
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
