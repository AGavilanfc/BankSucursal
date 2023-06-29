package com.optimissa.training.entityservice.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.optimissa.training.entityservice.models.entity;
import com.optimissa.training.entityservice.repository.entityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class entityService {
    Logger logger = LoggerFactory.getLogger(entityService.class);
    @Autowired
    private entityRepository entityRepository;
    public List<entity> getAll(){
        return entityRepository.getAll();
    }

    public entity getEntityById(int id) {
            Optional<entity> entity  = Optional.ofNullable(entityRepository.findById(id));
            if (entity.isPresent()) {
                logger.info("Searching a country by ID");

                return entity.get();
            } else {
                logger.warn("Currency with id {} not found", id);
                return null;
            }

    }
}
