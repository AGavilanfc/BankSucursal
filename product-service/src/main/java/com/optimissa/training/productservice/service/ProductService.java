package com.optimissa.training.productservice.service;

import com.optimissa.training.productservice.model.Product;
import com.optimissa.training.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    @Autowired
    private ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public int saveProduct (Product product) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        return productRepository.save(product, formattedDate);
    }

    public Product getProductById (int id)                                                                              {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts () {
        return productRepository.findAll();
    }

    public List<Product> getProductByName (String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getProductByActive (){
        return productRepository.findByActive();
    }

    public List<Product> getProductByInactive (){
        return productRepository.findByInactive();
    }

    public int deleteProduct (int id) {
        return productRepository.delete(id);
    }

    public int updateProduct (int id, Product product) {
        return productRepository.update(id, product);
    }


}
