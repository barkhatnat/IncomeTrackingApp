package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.DTO.CategoryDto;
import ru.barkhatnat.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories/{categoryId:\\d+}")
public class CategoryRestController {
    private final CategoryService categoryService;

    @PatchMapping
    public ResponseEntity<?> updateCategory(@PathVariable("categoryId") int categoryId, @Valid @RequestBody CategoryDto categoryDto,
                                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            categoryService.updateCategory(categoryId, categoryDto.title(), categoryDto.categoryType());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
