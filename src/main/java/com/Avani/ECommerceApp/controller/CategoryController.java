package com.Avani.ECommerceApp.controller;

import com.Avani.ECommerceApp.model.Category;
import com.Avani.ECommerceApp.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


//business logic in server and controller handles only routing of requests where it should go and what it should return. How to calculate the return value or storing process is not done by controller but rather service
@RestController
public class CategoryController {
private CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
}
    @GetMapping("/api/public/categories")
    public List<Category> getCategoryList() {
return categoryService.getAllCategories();
    }

    @PostMapping("/api/admin/add_category")
    public String addCategory(@RequestBody Category category) {
    return categoryService.createCategory(category);
    }
}
