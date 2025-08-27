package com.Avani.ECommerceApp.controller;

import com.Avani.ECommerceApp.model.Category;
import com.Avani.ECommerceApp.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


//business logic in server and controller handles only routing of requests where it should go and what it should return. How to calculate the return value or storing process is not done by controller but rather service
@RestController
public class CategoryController {
private final CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
}
    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getCategoryList() {
//return categoryService.getAllCategories();
        List<Category> categoryList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/api/admin/add_category")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        try {
            categoryService.createCategory(category);

            return new ResponseEntity<>("Created Successfully", HttpStatus.OK);
        }
        catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

@DeleteMapping("/api/admin/categories/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
//        return categoryService.deleteCategory(categoryId);
        try{
            String status = categoryService.deleteCategory(categoryId);
//            return new ResponseEntity<>(status, HttpStatus.OK);
//            return ResponseEntity.ok(status);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        }
        catch (ResponseStatusException rse){
            return new ResponseEntity<>(rse.getReason(), rse.getStatusCode());
        }
    }


    @PutMapping("/api/admin/update/{categoryName}_{updatedName")
public ResponseEntity<String> updateCategory(@PathVariable String categoryName, @PathVariable String updatedName) {
    try {
            categoryService.updateCategory(categoryName, updatedName);  
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }
    catch (ResponseStatusException rse){
    return new ResponseEntity<>(rse.getReason(), rse.getStatusCode());
    }
    }
}



