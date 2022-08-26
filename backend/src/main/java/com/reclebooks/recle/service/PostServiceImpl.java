package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.*;
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
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final FileHandler fileHandler;

    private final PostCategoryRepository postCategoryRepository;
        
    @Override
    public Long createPost(PostDto postDto, List<MultipartFile> files) throws Exception {

        User user = userRepository.findById(postDto.getUserId()).get();

        Book book = Book.createBook(postDto);

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
    public PostListDto getPostAll() throws IOException {

        List<GetPostDto> postDtos = new ArrayList<>();

        List<Post> all = postRepository.findAll();
        for (Post post : all) {
            List<Photo> photoList = post.getPhotoList();
            if(!photoList.isEmpty()){  // 사진이 있으면

                String path = photoList.get(0).getPhotoPath(); //이미지 하나 불러오기

                String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
                InputStream imageStream = new FileInputStream(absolutePath + path);
                byte[] imageByteArray = IOUtils.toByteArray(imageStream);
                imageStream.close();

                postDtos.add(GetPostDto.from(post, imageByteArray));
            }else {
                postDtos.add(GetPostDto.from(post, null));
            }
        }
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
    public PostListDto getPostFilterByCategory(Long categoryId) throws IOException {

        List<Post> postList = new ArrayList<>();

        List<GetPostDto> postDtos = new ArrayList<>();

        Category findCategory = categoryService.getCategoryById(categoryId);

        if(findCategory.getDepth() == 1) { //자식 카테고리없음
            postList = postCategoryRepository.findAllByCategoryId(categoryId).stream()
                    .map(postCategory -> postCategory.get().getPost())
                    .collect(Collectors.toList());
        }else{ //깊이가 0 자식 카테고리가 있음 --> 자식 카테고리에 해당하는 post모두 포함

            List<Long> categoryIds = findCategory.getChild().stream()
                    .map(category -> category.getId())
                    .collect(Collectors.toList());

            for (Long id : categoryIds) {
                List<Post> collect = postCategoryRepository.findAllByCategoryId(id)
                        .stream()
                        .map(postCategory -> postCategory.get().getPost())
                        .collect(Collectors.toList());
                for (Post post : collect) {
                    if(!postList.contains(post)){ //중복검사
                        postList.add(post);
                    }
                }
            }
        }

        for (Post post : postList) {
            List<Photo> photoList = post.getPhotoList();
            if(!photoList.isEmpty()){  // 사진이 있으면

                String path = photoList.get(0).getPhotoPath(); //이미지 하나 불러오기

                String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
                InputStream imageStream = new FileInputStream(absolutePath + path);
                byte[] imageByteArray = IOUtils.toByteArray(imageStream);
                imageStream.close();

                postDtos.add(GetPostDto.from(post, imageByteArray));
            }else {
                postDtos.add(GetPostDto.from(post, null));
            }
        }
        return new PostListDto(postDtos.size(),postDtos);
    }


}
