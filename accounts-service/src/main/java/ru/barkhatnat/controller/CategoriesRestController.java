package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.barkhatnat.DTO.CategoryDto;
import ru.barkhatnat.DTO.CategoryResponseDto;
import ru.barkhatnat.entity.Category;
import ru.barkhatnat.service.CategoryService;
import ru.barkhatnat.utils.CategoryMapper;

import java.net.URI;


@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriesRestController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<Iterable<CategoryResponseDto>> getCategoryList() {
        Iterable<Category> categories = categoryService.findAllCategories();
        Iterable<CategoryResponseDto> categoryResponseDto = categoryMapper.toCategoryResponseDtoCollection(categories);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto,
                                            BindingResult bindingResult,
                                            UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            CategoryResponseDto categoryResponseDto = categoryService.createCategory(categoryDto);
            return ResponseEntity.created(URI.create(uriComponentsBuilder
                            .replacePath("/categories")
                            .build().toUriString()))
                    .body(categoryResponseDto);
        }
    }
}
