package com.Avani.ECommerceApp.service;


import com.Avani.ECommerceApp.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//for loose coupling
public interface CategoryService {
    List<Category>
     getAllCategories();

    String createCategory(Category category);
}
