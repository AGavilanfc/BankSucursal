package com.optimissa.training.countryservice.service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.optimissa.training.countryservice.models.country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.optimissa.training.countryservice.repository.countryRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class countryService {

    Logger logger = LoggerFactory.getLogger(countryService.class);
    @Autowired
    private countryRepository countryRepository;

    public List<country> getAllCountries() {
        try {
            logger.info("Getting all the countries");
            return countryRepository.getAll();
        } catch (Exception e) {
            logger.error("An error occurred while getting all countries:{}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public country getCountryById(int id) {
        try {
            Optional<country> country  = Optional.ofNullable(countryRepository.findById(id));
            if (country.isPresent()) {
                logger.info("Searching a country by ID");

                return country.get();
            } else {
                logger.warn("Currency with id {} not found", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("An error occurred while getting currency with id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error occurred while retrieving currency with id " + id);

        }
    }
}
