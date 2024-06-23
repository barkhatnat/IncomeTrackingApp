package ru.barkhatnat.service;

import ru.barkhatnat.DTO.CategoryDto;
import ru.barkhatnat.DTO.CategoryResponseDto;
import ru.barkhatnat.entity.Category;

import java.util.Optional;

public interface CategoryService {
    Iterable<Category> findAllCategories();

    CategoryResponseDto createCategory(CategoryDto categoryDto);

    Optional<Category> findCategory(int id);

    void updateCategory(Integer id, String title, Boolean categoryType);

    void deleteCategory(int id);
}
