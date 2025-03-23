package service.impl;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CategoryRepo;
import service.ICategoryService;

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
