package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.Category;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IProductSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private IProductSrevice productService;

    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productsList";
    }
    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("errorMessage", "Product not found");
            return "error";
        }
        model.addAttribute("product", product);
        return "product-detail";
    }
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        return "add-product";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.getAllCategories();

        if (product == null) {
            model.addAttribute("errorMessage", "Product not found");
            return "error";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "edit-product";
    }
    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/products";
    }
}
