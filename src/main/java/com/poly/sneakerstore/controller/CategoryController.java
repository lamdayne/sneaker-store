package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateCategoryRequest;
import com.poly.sneakerstore.dto.request.UpdateCategoryRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseSuccess createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Create category successfully",
                categoryService.createCategory(request)
        );
    }

    @PutMapping("/{categoryId}")
    public ResponseSuccess updateCategory(@PathVariable String categoryId, @RequestBody @Valid UpdateCategoryRequest request) {
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Update category successfully",
                categoryService.updateCategory(categoryId, request)
        );
    }

    @DeleteMapping("/{categoryId}")
    public ResponseSuccess deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseSuccess(
                HttpStatus.NO_CONTENT,
                "Delete category successfully"
        );
    }

    @GetMapping("/{categoryId}")
    public ResponseSuccess getCategory(@PathVariable String categoryId) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get category by id successfully",
                categoryService.getCategoryById(categoryId)
        );
    }

    @GetMapping
    public ResponseSuccess getAllCategories(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "5", required = false) int pageSize,
            @RequestParam(required = false) String sortBy
    ) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get all categories successfully",
                categoryService.getAllCategories(pageNo, pageSize, sortBy)
        );
    }

    @PatchMapping("/{categoryId}")
    public ResponseSuccess changeStatus(@PathVariable String categoryId, @RequestParam Boolean status) {
        categoryService.changeStatus(categoryId, status);
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Change status successfully"
        );
    }

}
