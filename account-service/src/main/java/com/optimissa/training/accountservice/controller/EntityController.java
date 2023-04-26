package com.optimissa.training.accountservice.controller;

import com.optimissa.training.accountservice.models.Entity;
import com.optimissa.training.accountservice.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entity")
public class EntityController {

    @Autowired
    private EntityService entityService;
    @GetMapping("")
    public List<Entity> getAllEntities(){

        return entityService.getAll();

    }
}
