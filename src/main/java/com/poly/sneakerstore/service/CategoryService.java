package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateCategoryRequest;
import com.poly.sneakerstore.dto.request.UpdateCategoryRequest;
import com.poly.sneakerstore.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryRequest request);
    CategoryResponse updateCategory(String categoryId, UpdateCategoryRequest request);
    void deleteCategory(String categoryId);
    CategoryResponse getCategoryById(String categoryId);
    List<CategoryResponse> getAllCategories();
}
