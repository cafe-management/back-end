package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface IProductSrevice {
    void addProduct(Product product);
    Product getProductById(Long id);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
}
