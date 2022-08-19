package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Category;
import com.reclebooks.recle.dto.categorydto.CategoryDto;
import com.reclebooks.recle.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{


    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getCategoryAll() {
        return null;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();

        return category;
    }

    @Override
    public Long createCategory(Category category) {
        Category saveCategory = categoryRepository.save(category);
        return saveCategory.getId();
    }
}
