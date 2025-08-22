package com.Avani.ECommerceApp.service;

import com.Avani.ECommerceApp.controller.CategoryController;
import com.Avani.ECommerceApp.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

// since id is a unique identifier, we can't rely on the user to give us that id. therefore we create a variable for it and update it. so even if the user gives an id, it will update and store the current id.
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

    public String deleteCategory(@RequestBody int id) {
        Category category = categoryList.stream().filter(c-> c.getCategoryId() == id).findFirst().orElse(null);
        if(category==null){
            return "Category not found";
        }
        categoryList.remove(category);
        return "Deleted";
    }
}
