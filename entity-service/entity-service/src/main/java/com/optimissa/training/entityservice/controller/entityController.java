package com.optimissa.training.entityservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.optimissa.training.entityservice.models.entity;
import com.optimissa.training.entityservice.service.entityService;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/entity")
public class entityController {

    Logger logger = LoggerFactory.getLogger(entityService.class);
    @Autowired
    private entityService entityService;
    @GetMapping("/get-all")
    public List<entity> getAllEntities(){
        return entityService.getAll();
    }

    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Object> getEntityById(@PathVariable int id) {
        try {
            Long start = System.currentTimeMillis();
            entity entity = entityService.getEntityById(id);
            Long end = System.currentTimeMillis();
            logger.info("getCurrencyById, Time: {}", (end - start));
            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }
    }

}
