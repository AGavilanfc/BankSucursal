package com.optimissa.training.productservice.mapper;

import com.optimissa.training.productservice.api.ProductRequest;
import com.optimissa.training.productservice.model.Product;

public class ProductRequestMapper {
    public static Product mapToProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        return product;
    }
}
