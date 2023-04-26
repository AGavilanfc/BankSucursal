package com.optimissa.training.productservice.repository;

import com.optimissa.training.productservice.model.Product;

import java.util.List;

public interface ProductRepository {

    int save(Product product);

    List<Product> findAll();
    Product findById(int id);
    List<Product> findByName(String name);
    List<Product> findByActive();
    List<Product> findByInactive();
    int delete(int id);
    int update(int id, Product product);
}
