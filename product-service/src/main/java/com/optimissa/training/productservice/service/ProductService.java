package com.optimissa.training.productservice.service;

import com.optimissa.training.productservice.model.Product;
import com.optimissa.training.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    private ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public int saveProduct (Product product) {
        return productRepository.save(product);
    }

    public Product getProductById (int id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts () {
        return productRepository.findAll();
    }
}
