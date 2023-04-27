package com.optimissa.training.productservice.controller;

import com.optimissa.training.productservice.model.Product;
import com.optimissa.training.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<String> saveProduct (@RequestBody Product product) {
        logger.info("Calling saveProduct()");
        int result = productService.saveProduct(product);
        if (result > 0) {
            return ResponseEntity.ok("Product created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create new product");
        }
        }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Calling getAllProducts()");
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        logger.info("Calling getProductById for id {}", id);
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            logger.error("Product with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
        logger.info("Calling getProductByName for {}", name);
        List<Product> products = productService.getProductByName(name);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all/active")
    public ResponseEntity<List<Product>> getActives() {
        logger.info("Calling getActives");
        List<Product> products = productService.getProductByActive();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all/inactive")
    public ResponseEntity<List<Product>> getInactives() {
        logger.info("Calling getInactives");
        List<Product> products = productService.getProductByInactive();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        logger.info("Calling deleteProduct for id {}", id);
        int result = productService.deleteProduct(id);
        if (result > 0) {
            return new ResponseEntity<>("Product with id " + id + " has been successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product with id " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        logger.info("Calling updateProduct for id {}", id);
        int result = productService.updateProduct(id, product);
        if (result > 0) {
            return new ResponseEntity<>("Product with id " + id + " has been successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product with id " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }
}
