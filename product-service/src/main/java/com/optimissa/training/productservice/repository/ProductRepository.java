package com.optimissa.training.productservice.repository;

import com.optimissa.training.productservice.model.Product;

import java.util.List;

public interface ProductRepository {

    int save(Product product);

    List<Product> findAll();
}
