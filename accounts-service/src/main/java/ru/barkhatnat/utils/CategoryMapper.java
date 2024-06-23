package ru.barkhatnat.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.barkhatnat.DTO.AccountDto;
import ru.barkhatnat.DTO.AccountResponseDto;
import ru.barkhatnat.DTO.CategoryDto;
import ru.barkhatnat.DTO.CategoryResponseDto;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);
    CategoryResponseDto toCategoryResponseDto(Category category);

    Iterable<CategoryDto> toCategoryDtoCollection(Iterable<Category> categories);

    Iterable<CategoryResponseDto> toCategoryResponseDtoCollection(Iterable<Category> categories);
}
