package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.*;
import com.reclebooks.recle.dto.CategoryDto;
import com.reclebooks.recle.dto.PostDto;
import com.reclebooks.recle.dto.getPostDto;
import com.reclebooks.recle.repository.CategoryRepository;
import com.reclebooks.recle.repository.PostRepository;
import com.reclebooks.recle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final CategoryService categoryService;


    @Override
    public List<getPostDto> getPostAll() {
        return null;
    }

    @Override
    public getPostDto getPostOneByPostId(Long postId) {
        return null;
    }

    @Override
    public Long createPost(PostDto postDto) {

        // category
        Category category = categoryService.getCategoryById(postDto.getCategoryId());

        PostCategory postCategory = new PostCategory();
        postCategory.setCategory(category);

        //User 정보
        User findUser = userRepository.findById(postDto.getUserId()).get();

        Book book = Book.createBook(postDto);

        BookState bookState = BookState.createBookState(postDto);

        Post post = Post.createPost(postDto, book, bookState);

        Post savePost = postRepository.save(post);

        post.addPostCategory(postCategory);

        return savePost.getId();

    }

    @Override
    public Long updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public Long deletePost(PostDto postDto) {
        return null;
    }
}
