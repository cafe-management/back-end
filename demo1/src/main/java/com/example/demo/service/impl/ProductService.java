package com.example.demo.service.impl;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.IProductSrevice;

import java.util.List;

@Service
public class ProductService implements IProductSrevice {
    @Autowired
    private ProductRepo productRepository;

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
