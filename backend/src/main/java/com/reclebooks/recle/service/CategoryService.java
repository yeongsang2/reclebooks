package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Category;
import com.reclebooks.recle.dto.categorydto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {



    //category 불러오기
    public List<Category> getCategoryAll();


    //id로 category 가져오기
    public Category getCategoryById(Long categoryId);

    //category 등록하기 admin?
    public Long createCategory(Category category);


    public List<Category> getCategoryChild(Long categoryId);
}
