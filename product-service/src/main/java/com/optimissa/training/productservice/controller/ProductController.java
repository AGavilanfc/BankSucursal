package com.optimissa.training.productservice.controller;

import com.optimissa.training.productservice.api.ProductRequest;
import com.optimissa.training.productservice.model.Product;
import com.optimissa.training.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        logger.info("Calling saveProduct()");
        try {
            long startTime = System.currentTimeMillis();
            int result = productService.saveProduct(product);
            long endTime = System.currentTimeMillis();
            if (result == 1) {
                logger.info("Product created successfully. Execution took {}ms.", endTime - startTime);
                return ResponseEntity.ok("Product created successfully");
            } else {
                logger.error("Failed to create new product. Execution took {}ms.", endTime - startTime);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to create new product");
            }
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllProducts() {
        logger.info("Calling getAllProducts()");
        try {
            long startTime = System.currentTimeMillis();
            List<Product> products = productService.getAllProducts();
            long endTime = System.currentTimeMillis();
            if (products.isEmpty()) {
                logger.warn("List of Products is Empty. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("Showing all products. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        logger.info("Calling getProductById for id {}", id);
        try {
            long startTime = System.currentTimeMillis();
            Product product = productService.getProductById(id);
            long endTime = System.currentTimeMillis();
            if (product != null) {
                logger.info("Showing requested product. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                logger.error("Product with id {} not found. Execution took {}ms.", id, endTime - startTime);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Id not found");
            return new ResponseEntity<>("Id not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<Object> getProductByName(@PathVariable String name) {
        logger.info("Calling getProductByName for {}", name);
        try {
            long startTime = System.currentTimeMillis();
            List<Product> products = productService.getProductByName(name);
            long endTime = System.currentTimeMillis();
            if (!products.isEmpty()) {
                logger.info("Showing requested product. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                logger.error("Product with name {} not found. Execution took {}ms.", name, endTime - startTime);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Name not found");
            return new ResponseEntity<>("Name not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all/active")
    public ResponseEntity<Object> getActives() {
        logger.info("Calling getActives");
        try {
            long startTime = System.currentTimeMillis();
            List<Product> products = productService.getProductByActive();
            long endTime = System.currentTimeMillis();
            if (!products.isEmpty()) {
                logger.info("Showing list of active products. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                logger.error("Active products not found. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all/inactive")
    public ResponseEntity<Object> getInactives() {
        logger.info("Calling getInactives");
        try {
            long startTime = System.currentTimeMillis();
            List<Product> products = productService.getProductByInactive();
            long endTime = System.currentTimeMillis();
            if (!products.isEmpty()) {
                logger.info("Showing list of inactive products. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                logger.error("Inactive products not found. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        logger.info("Calling deleteProduct for id {}", id);
        try {
            long startTime = System.currentTimeMillis();
            int result = productService.deleteProduct(id);
            long endTime = System.currentTimeMillis();
            if (result == 1) {
                logger.info("Product has been successfully deleted. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>("Product with id " + id + " has been successfully deleted", HttpStatus.OK);
            } else {
                logger.error("Product with id {} not found. Execution took {}ms.", id , endTime - startTime);
                return new ResponseEntity<>("Product with id " + id + " was not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") int id, @RequestBody ProductRequest productRequest) {
        logger.info("Calling updateProduct for id {}", id);
        try {
            long startTime = System.currentTimeMillis();
            int result = productService.updateProduct(id, productRequest);
            long endTime = System.currentTimeMillis();
            if (result == 1) {
                logger.info("Product has been successfully updated. Execution took {}ms.", endTime - startTime);
                return new ResponseEntity<>("Product with id " + id + " has been successfully updated", HttpStatus.OK);
            } else {
                logger.error("Product with id {} not found. Execution took {}ms.", id , endTime - startTime);
                return new ResponseEntity<>("Product with id " + id + " was not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
