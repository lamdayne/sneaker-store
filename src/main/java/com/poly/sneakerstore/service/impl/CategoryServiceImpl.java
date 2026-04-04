package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateCategoryRequest;
import com.poly.sneakerstore.dto.request.UpdateCategoryRequest;
import com.poly.sneakerstore.dto.response.CategoryResponse;
import com.poly.sneakerstore.dto.response.PageResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.CategoryMapper;
import com.poly.sneakerstore.model.Category;
import com.poly.sneakerstore.repository.CategoryRepository;
import com.poly.sneakerstore.service.CategoryService;
import com.poly.sneakerstore.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final PageableUtil pageableUtil;

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategory(String categoryId, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategory(category, request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public PageResponse<?> getAllCategories(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = pageableUtil.createPageable(pageNo, pageSize, sortBy);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryResponse> response = categories.stream().map(categoryMapper::toCategoryResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(categories.getTotalPages())
                .totalElements((int) categories.getTotalElements())
                .items(response)
                .build();
    }

    @Override
    public void changeStatus(String categoryId, Boolean status) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setActive(status);
        categoryRepository.save(category);
    }
}
