package com.Avani.ECommerceApp.service;


import com.Avani.ECommerceApp.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//for loose coupling
public interface CategoryService {
    List<Category>
     getAllCategories();

   void createCategory(Category category);

    String deleteCategory(int categoryId);

    Category updateCategory(Category category, int categoryId);
//    void updateCategory(String categoryName, String updatedName);
}
