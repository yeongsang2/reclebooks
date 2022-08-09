package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.*;
import com.reclebooks.recle.dto.CategoryDto;
import com.reclebooks.recle.dto.PostDto;
import com.reclebooks.recle.dto.GetPostDto;
import com.reclebooks.recle.repository.PostRepository;
import com.reclebooks.recle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryService categoryService;


    @Override
    public List<GetPostDto> getPostAll() {

        return postRepository.findAll().stream()
                .map(post -> GetPostDto.from(post))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostOneByPostId(Long postId) {
        return PostDto.from(postRepository.findById(postId).get());
    }
        
    @Override
    public Long createPost(PostDto postDto) {

        User user = userRepository.findById(postDto.getUserId()).get();

        Book book = Book.createBook(postDto);

        BookState bookState = BookState.createBookState(postDto);

        Post post = Post.createPost(postDto, user, book, bookState);

        // category 설정
        for (CategoryDto categoryDto : postDto.getCategoryDtos()) {
            Category categoryById = categoryService.getCategoryById(categoryDto.getId());
            PostCategory postCategory = new PostCategory();
            postCategory.setCategory(categoryById); // 연관관계 주인
            postCategory.setPost(post);
        }
        return postRepository.save(post).getId();
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
