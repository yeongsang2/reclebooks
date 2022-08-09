package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Category;
import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.dto.CategoryDto;
import com.reclebooks.recle.dto.GetPostDto;
import com.reclebooks.recle.dto.PostDto;
import com.reclebooks.recle.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceImplTest {

    @Autowired
    private PostService postService;
    @Autowired private CategoryService categoryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

    @Test
    @Rollback(value = false)
    public void craetePost(){

        //given
        User user = userRepository.findOneWithuserAuthoritiesByUsername("yeongsang").get();

        Category category = new Category();  //category
        category.setName("자바");
        categoryService.createCategory(category);
        Category category1 = new Category();
        category1.setName("컴퓨터");
        categoryService.createCategory(category1);

        PostDto postDto = new PostDto();

        postDto.getCategoryDtos().add(CategoryDto.from(category));
        postDto.getCategoryDtos().add(CategoryDto.from(category1));

        postDto.setUserId(user.getId());

        postDto.setTitle("책팜");
        postDto.setDescription("책팔아용");
        postDto.setPrice("10000");

        postDto.setBookName("jpa");
        postDto.setBookAuthor("김영한");
        postDto.setBookPrice("200000");
        postDto.setBookPublisher("조은출판");
        postDto.setIsbn("2315");
        postDto.setPublishDate("20200303");

        //when
        Long postId = postService.createPost(postDto);

        //then

        PostDto findPostDto = postService.getPostOneByPostId(postId);
        System.out.println("--------------------------------------");
        System.out.println(findPostDto.toString());
        System.out.println("--------------------------------------");

    }
    @Test
    @Rollback(value = false)
    public void craetePostAll() {

        User user = userRepository.findOneWithuserAuthoritiesByUsername("yeongsang").get();

        Category category = new Category();  //category
        category.setName("자바");
        categoryService.createCategory(category);
        Category category1 = new Category();
        category1.setName("컴퓨터");
        categoryService.createCategory(category1);

        PostDto postDto = new PostDto();

        postDto.getCategoryDtos().add(CategoryDto.from(category));
        postDto.getCategoryDtos().add(CategoryDto.from(category1));

        postDto.setUserId(user.getId());

        postDto.setTitle("책팜");
        postDto.setDescription("책팔아용");
        postDto.setPrice("10000");

        postDto.setBookName("jpa");
        postDto.setBookAuthor("김영한");
        postDto.setBookPrice("200000");
        postDto.setBookPublisher("조은출판");
        postDto.setIsbn("2315");
        postDto.setPublishDate("20200303");

        PostDto postDto1 = new PostDto();

        postDto1.getCategoryDtos().add(CategoryDto.from(category));
        postDto1.getCategoryDtos().add(CategoryDto.from(category1));

        postDto1.setUserId(user.getId());

        postDto1.setTitle("책팜");
        postDto1.setDescription("책팔아용");
        postDto1.setPrice("10000");

        postDto1.setBookName("jpa");
        postDto1.setBookAuthor("김영한");
        postDto1.setBookPrice("200000");
        postDto1.setBookPublisher("조은출판");
        postDto1.setIsbn("2315");
        postDto1.setPublishDate("20200303");

        //when
        Long postId = postService.createPost(postDto);
        Long postId1 = postService.createPost(postDto1);

        //
        List<GetPostDto> postAll = postService.getPostAll();
        System.out.println("=================================");
        System.out.println(postAll);
        System.out.println("=================================");


    }

}