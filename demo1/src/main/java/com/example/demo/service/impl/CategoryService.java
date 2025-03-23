package com.example.demo.service.impl;

import com.example.demo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.service.ICategoryService;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
