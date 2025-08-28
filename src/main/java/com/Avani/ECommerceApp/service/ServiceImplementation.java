package com.Avani.ECommerceApp.service;

import com.Avani.ECommerceApp.controller.CategoryController;
import com.Avani.ECommerceApp.model.Category;
import com.Avani.ECommerceApp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

// since id is a unique identifier, we can't rely on the user to give us that id. therefore we create a variable for it and update it. so even if the user gives an id, it will update and store the current id.
@Service
public class ServiceImplementation implements CategoryService{
    int nextId=0;
//    private List<Category> categoryList = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
//        return categoryList;
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
//        categoryList.add(category);
        categoryRepository.save(category);
    }

    public String deleteCategory(@RequestBody int id) {
Category category = categoryRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.OK, "resource not found"));
//        List<Category> categoryList = categoryRepository.findAll();
//        Category category = categoryList.stream().filter(c-> c.getCategoryId() == id).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, " Category not found"));

//        categoryList.remove(category);
        categoryRepository.delete(category);
        return "Deleted";
    }

    @Override
    public Category updateCategory(Category category, int categoryId) {
//        List<Category> categoryList = categoryRepository.findAll();
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);
        Category savedCategory = savedCategoryOptional.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "resource not found"));
        category.setCategoryId(categoryId);
        savedCategory =categoryRepository.save(category);
        return savedCategory;
//        Optional<Category> optionalCategory = categoryList.stream().filter(c->c.getCategoryId() == categoryId).findFirst();
//        if(optionalCategory.isPresent()){
//            Category currentCategory = optionalCategory.get();
//            currentCategory.setCategoryName(category.getCategoryName());
//            Category updatedCategory = categoryRepository.save(currentCategory);
//            return updatedCategory;
//        }
//        else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Category not found");
//        }
    }
}
