package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Category;
import com.reclebooks.recle.repository.CategoryRepository;
import com.reclebooks.recle.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryServiceImplTest {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Rollback(value = false)
    @Test
    public void createCategory(){


        //given
        Category category = new Category();
        category.setName("컴퓨터");

        //when
        Long findId = categoryService.createCategory(category);

        //then
        Category findCategory = categoryService.getCategoryById(findId);
        Assertions.assertThat("컴퓨터").isEqualTo(findCategory.getName());

    }

    @Rollback(value = false)
    @Test
    public void 이름으로조회 (){


        //given
        Category category = new Category();
        category.setName("컴퓨터");

        //when
        Long findId = categoryService.createCategory(category);

        //then
        Category findCategory = categoryRepository.findOneByName("컴퓨터").get();
        Assertions.assertThat("컴퓨터").isEqualTo(findCategory.getName());

    }
}