package controller;

import model.Product;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.impl.CategoryService;
import service.impl.ProductService;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productsList";
    }

   @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "products-detail";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        return "products/add";
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
}
