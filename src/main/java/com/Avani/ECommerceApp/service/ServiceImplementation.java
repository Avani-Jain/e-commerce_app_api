package com.Avani.ECommerceApp.service;

import com.Avani.ECommerceApp.controller.CategoryController;
import com.Avani.ECommerceApp.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImplementation implements CategoryService{
    int nextId=0;
    private List<Category> categoryList = new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categoryList;
    }

    @Override
    public String createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryList.add(category);
        return "Added";
    }
}
