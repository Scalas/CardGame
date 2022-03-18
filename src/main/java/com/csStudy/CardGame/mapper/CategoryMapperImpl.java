package com.csStudy.CardGame.mapper;

import com.csStudy.CardGame.domain.Category;
import com.csStudy.CardGame.dto.CategoryDto;

public class CategoryMapperImpl implements CategoryMapper{

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        else {
            Category category = new Category();
            category.setCname(categoryDto.getCname());
            return category;
        }
    }

    @Override
    public CategoryDto toDto(Category category) {
        if (category == null) {
            return null;
        }
        else {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCid(category.getCid());
            categoryDto.setCname(category.getCname());
            return categoryDto;
        }
    }

}