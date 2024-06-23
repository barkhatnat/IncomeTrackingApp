package ru.barkhatnat.service;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.DTO.CategoryDto;
import ru.barkhatnat.DTO.CategoryResponseDto;
import ru.barkhatnat.entity.Category;
import ru.barkhatnat.entity.User;
import ru.barkhatnat.repositories.CategoryRepository;
import ru.barkhatnat.utils.CategoryMapper;

import ru.barkhatnat.utils.SecurityUtil;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public Iterable<Category> findAllCategories() {
        Collection<Category> defaultCategories = categoryRepository.findCategoriesByUserEmpty();
        Integer id = SecurityUtil.getCurrentUserDetails().getUserId();
        Collection<Category> customCategories = (Collection<Category>) userService.findAllUserCategories(id);
        return Iterables.concat(defaultCategories, customCategories);
    }

    @Override
    @Transactional
    public CategoryResponseDto createCategory(CategoryDto categoryDto) {
        Integer id = SecurityUtil.getCurrentUserDetails().getUserId();
        Optional<User> user = userService.findUser(id);
        if (user.isEmpty()) {
            throw new NoSuchElementException(); //TODO сделать кастомный эксепшн
        }
        Category category = categoryRepository.save(new Category(categoryDto.title(), categoryDto.categoryType(), user.get()));
        return categoryMapper.toCategoryResponseDto(category);
    }

    @Override
    @Transactional
    public Optional<Category> findCategory(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateCategory(Integer id, String title, Boolean categoryType) {
        categoryRepository.findById(id).ifPresentOrElse(category -> {
            if (category.getUser() != null && category.getUser().getId().equals(SecurityUtil.getCurrentUserDetails().getUserId())) {
                category.setTitle(title);
                category.setCategoryType(categoryType);
            } else {
                throw new IllegalArgumentException("You do not have permission to update this category.");
            }
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    @Transactional
    public void deleteCategory(int id) {
        categoryRepository.findById(id).ifPresentOrElse(account -> {
                    if (account.getUser() != null && account.getUser().getId().equals(SecurityUtil.getCurrentUserDetails().getUserId())) {
                        categoryRepository.deleteById(id);
                    } else {
                        throw new IllegalArgumentException("You do not have permission to delete this category.");
                    }
                }, () -> {
                    throw new NoSuchElementException();
                }
        );
    }
}
