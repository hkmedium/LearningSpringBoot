package com.hk.learningspringboot.service;

import com.hk.learningspringboot.entity.Category;
import com.hk.learningspringboot.exception.ResourceAlreadyExistsException;
import com.hk.learningspringboot.exception.ResourceNotFoundException;
import com.hk.learningspringboot.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.hk.learningspringboot.utill.Constants.*;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepo categoryRepo;

    public String createCategory(Category category) {
        try {
            categoryRepo.save(category);
            return SUCCESS_TEXT;
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException(CATEGORY_TEXT);
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }

    public String updateCategory(int categoryId, Category updatedCategory) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (category.isPresent()) {
            categoryRepo.save(updatedCategory);
            return SUCCESS_TEXT;
        }
        throw new ResourceNotFoundException("Category not found");
    }

    public String deleteCategory(int categoryId) {
        categoryRepo.deleteById(categoryId);
        return SUCCESS_TEXT;
    }

    public Category getCategoryById(int categoryId) {
        try {
//            if (categoryRepo.findById(categoryId).isEmpty()) {
//                throw new ItemAlreadyExistsException("Requested Category");
//                throw new ItemNotFoundException("Requested Category");
//            }
            return categoryRepo.findById(categoryId).get();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException(CATEGORY_TEXT);
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_ERROR_TEXT);
        }
    }
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

}
