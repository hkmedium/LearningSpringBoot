package com.hk.learningspringboot.controller;

import com.hk.learningspringboot.entity.Category;
import com.hk.learningspringboot.response.ResponseHandler;
import com.hk.learningspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import static com.hk.learningspringboot.utill.Constants.REQUESTED_CATEGORY_TEXT;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        return ResponseHandler.responseBuilder(categoryService.createCategory(category),
                HttpStatus.CREATED, null);
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> getAllCategory() {
        return ResponseHandler.responseBuilder(REQUESTED_CATEGORY_TEXT,
                HttpStatus.OK, categoryService.getAllCategories());
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Object> getCategoryById(@PathVariable("categoryId") int categoryId) {
        return ResponseHandler.responseBuilder(REQUESTED_CATEGORY_TEXT,
                HttpStatus.OK, categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<Object> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category updatedCategory) {
        return ResponseHandler.responseBuilder(categoryService.updateCategory(categoryId, updatedCategory),
                HttpStatus.OK, null);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable("categoryId") int categoryId) {
        return ResponseHandler.responseBuilder(categoryService.deleteCategory(categoryId),
                HttpStatus.OK, null);
    }
}
