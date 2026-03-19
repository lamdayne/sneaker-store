package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateCategoryRequest;
import com.poly.sneakerstore.dto.request.UpdateCategoryRequest;
import com.poly.sneakerstore.dto.response.CategoryResponse;
import com.poly.sneakerstore.model.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CreateCategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryResponseList(List<Category> categoryList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategory(@MappingTarget Category category, UpdateCategoryRequest request);
}
