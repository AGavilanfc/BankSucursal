package com.optimissa.training.productservice.controller;

import com.optimissa.training.productservice.model.Product;
import com.optimissa.training.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public int saveProduct (@RequestBody Product product) {
        logger.info("Calling saveProduct()");
        return productService.saveProduct(product);
    }

    @GetMapping("/get-all")
    public List<Product> getAllProducts() {
        logger.info("Calling getAllProducts()");
        return productService.getAllProducts();
    }

    @GetMapping("/get-by-id/{id}")
    public Product getProductById (@PathVariable int id) {
        logger.info("Calling getProductById for {} id ", id);
        return productService.getProductById(id);
    }

    @GetMapping("/get-by-name/{name}")
    public List<Product> getProductByName (@PathVariable String name) {
        logger.info("Calling getProductByName for {} ", name);
        return productService.getProductByName(name);
    }

    @GetMapping("/get-by-active/{active}")
    public List<Product> getActives (@PathVariable Boolean active) {
        logger.info("Calling getActives");
        return productService.getProductByActive(active);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteProduct (@PathVariable int id) {
        
        return productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public int updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
}
