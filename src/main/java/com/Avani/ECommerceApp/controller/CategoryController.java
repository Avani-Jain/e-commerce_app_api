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


//    request mapping is used when we are using urls like /api/categories... basically the url is constant for first part and according to mapping we are changing the second part. therefore. we can use requestmapping to tell that this first part has to be added for all mappings and the second part we will give. For this purpose, RequestMapping is used at class level
//    for endpoint defining. --> instead of mentioning getmapping postmapping etc. we can directly use requestmapping(value="url", method= RequestMethod.GET)
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


    @PutMapping("/api/admin/update/{categoryId}")
public ResponseEntity<String> updateCategory(@RequestBody Category category, @PathVariable int categoryId) {
    try {
            Category updatedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }
    catch (ResponseStatusException rse){
    return new ResponseEntity<>(rse.getReason(), rse.getStatusCode());
    }
    }
}



