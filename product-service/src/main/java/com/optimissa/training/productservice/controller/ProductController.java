package com.optimissa.training.productservice.controller;

import com.optimissa.training.productservice.model.Product;
import com.optimissa.training.productservice.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/id/{id}")
    public Product getProductById (@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/name/{name}")
    public List<Product> getProductByName (@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/active/{active}")
    public List<Product> getProductByActive (@PathVariable Boolean active) {
        return productService.getProductByActive(active);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct (@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public int updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
}
