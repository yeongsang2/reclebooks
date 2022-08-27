package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.*;
import com.reclebooks.recle.dto.bookdto.SearchBookDtoByIsbn;
import com.reclebooks.recle.dto.categorydto.CategoryDto;
import com.reclebooks.recle.dto.postdto.GetPostDto;
import com.reclebooks.recle.dto.postdto.PostDto;
import com.reclebooks.recle.dto.postdto.PostListDto;
import com.reclebooks.recle.dto.postdto.UpdatePostDto;
import com.reclebooks.recle.repository.PostCategoryRepository;
import com.reclebooks.recle.repository.PostRepository;
import com.reclebooks.recle.repository.UserRepository;
import com.reclebooks.recle.util.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final FileHandler fileHandler;

    private final BookService  bookService;

    private final PostCategoryRepository postCategoryRepository;
        
    @Override
    public Long createPost(PostDto postDto, List<MultipartFile> files) throws Exception {

        User user = userRepository.findById(postDto.getUserId()).get();

        //postDto 에서 isbn 불러옴 book service에서 젇보 받아와서 book return
        SearchBookDtoByIsbn searchBookDtoByIsbn = bookService.searchBookByIsbn(postDto.getIsbn());

        Book book = Book.createBook(searchBookDtoByIsbn);


        BookState bookState = BookState.createBookState(postDto);

        List<Photo> photoList = fileHandler.parseFileInfo(files);

        Post post = Post.createPost(postDto, user, book, bookState);

        // photo 저장
        if(!photoList.isEmpty()) {

            photoList.forEach(photo -> photo.setPost(post));

        }
        if(!postDto.getCategoryDtos().isEmpty()) {
            // category 설정
            for (CategoryDto categoryDto : postDto.getCategoryDtos()) {
                Category categoryById = categoryService.getCategoryById(categoryDto.getId());
                PostCategory postCategory = new PostCategory();
                postCategory.setCategory(categoryById); // 연관관계 주인
                postCategory.setPost(post);
            }
        }
        return postRepository.save(post).getId();
    }

    @Override
    public PostListDto getPostAll(String keyword) throws IOException {

        List<Post> postList;

        if(keyword == null){ // keyword 없으면
            postList = postRepository.findAll();
        }else{
            postList = postRepository.findAllByTitleContaining(keyword);
        }

        List<GetPostDto> postDtos = postList.stream()
                .map(post -> GetPostDto.from(post))
                .collect(Collectors.toList());

        return new PostListDto(postDtos.size(),postDtos);
    }

    @Override
    public Post getPostOneByPostId(Long postId) {

        return postRepository.findById(postId).get();
    }
    @Override
    public Long updatePost(UpdatePostDto updatePostDto) {

        Post post = postRepository.findById(updatePostDto.getPostId()).get();

        Post updatePost = post.updatePost(updatePostDto);

        return postRepository.save(updatePost).getId();
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId); // 성공하면? null이면 ?? 나중에 예외처리해야할
    }

    @Override
    public void addViewCount(Long postId) {
        Post post = postRepository.findById(postId).get();
        post.addViewCount();
    }

    @Override
    public void salesComplete(Long postId) {
        Post post = postRepository.findById(postId).get();
        post.setSell(true);
    }

    // 필터
    @Override
    public PostListDto getPostFilterByCategory(Long categoryId, String keyword) throws IOException {

        List<Post> postList = new ArrayList<>();

        Category findCategory = categoryService.getCategoryById(categoryId);

        if(findCategory.getDepth() == 1) { //자식 카테고리없음

            if(keyword == null) {
                postList = postCategoryRepository.findAllByCategoryId(categoryId).stream()
                        .map(postCategory -> postCategory.get().getPost())
                        .collect(Collectors.toList());
            }else{
                postList = postCategoryRepository.findAllByCategoryId(categoryId).stream()
                        .map(postCategory -> postCategory.get().getPost())
                        .filter(post -> post.getTitle().contains(keyword))
                        .collect(Collectors.toList());
            }
        }else{ //깊이가 0 자식 카테고리가 있음 --> 자식 카테고리에 해당하는 post모두 포함

            List<Long> categoryIds = findCategory.getChild().stream()
                    .map(category -> category.getId())
                    .collect(Collectors.toList());

            for (Long id : categoryIds) {

                List<Post> collect;

                if(keyword == null) {
                    collect = postCategoryRepository.findAllByCategoryId(id)
                            .stream()
                            .map(postCategory -> postCategory.get().getPost())
                            .collect(Collectors.toList());
                }else {
                    collect = postCategoryRepository.findAllByCategoryId(id)
                            .stream()
                            .map(postCategory -> postCategory.get().getPost())
                            .filter(post -> post.getTitle().contains(keyword))
                            .collect(Collectors.toList());
                }

                for (Post post : collect) {
                    if(!postList.contains(post)){ //중복검사
                        postList.add(post);
                    }
                }
            }
        }

        List<GetPostDto> postDtos = postList.stream()
                .map(post -> GetPostDto.from(post))
                .collect(Collectors.toList());

        return new PostListDto(postDtos.size(),postDtos);
    }


}
