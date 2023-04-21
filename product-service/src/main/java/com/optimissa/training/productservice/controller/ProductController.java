package com.optimissa.training.productservice.controller;

import com.optimissa.training.productservice.model.Product;
import com.optimissa.training.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public int saveProduct (@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById (@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.getAllProducts();
    }


}
