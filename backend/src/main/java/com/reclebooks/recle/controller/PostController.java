package com.reclebooks.recle.controller;

import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.dto.*;
import com.reclebooks.recle.repository.UserRepository;
import com.reclebooks.recle.service.CategoryService;
import com.reclebooks.recle.service.PostService;
import com.reclebooks.recle.util.SecurityUtil;
import com.reclebooks.recle.vo.PostFileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    private final CategoryService categoryService;
    //전체조회
    @GetMapping("/board") //전체조회
    public ResponseEntity<PostListDto> getPostAll(){
        PostListDto postAll = postService.getPostAll();

        return ResponseEntity.ok(postAll);
    }

    @GetMapping("/board/post/{postId}") // 단건조회
    public ResponseEntity<PostDto> getPostOne(@PathVariable Long postId){
        PostDto findPost = postService.getPostOneByPostId(postId);

        return ResponseEntity.ok(findPost);
    }

//    @PostMapping("/board/post")
//    @PreAuthorize("hasAnyRole('USER')") //user만 게시글 작성가능
//    public ResponseEntity<Long> createPost(@RequestBody PostDto postDto){
//
//        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null);
//        postDto.setUserId(user.getId());
//        return ResponseEntity.ok(postService.createPost(postDto));
//    }

    @PostMapping(value = "/board/post" ,  consumes = { MediaType.APPLICATION_JSON_VALUE,  MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("hasAnyRole('USER')") //user만 게시글 작성가능
    public ResponseEntity<Long> createPost(@RequestPart PostDto postDto, @RequestPart(required = false) List<MultipartFile> files) throws Exception {

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null);

        postDto.setUserId(user.getId());


        return ResponseEntity.ok(postService.createPost(postDto, files));
    }

    @PatchMapping("/board/post")
    @PreAuthorize("hasAnyRole('USER')") // 해당 사용자만 게시글 수정 가능
    public ResponseEntity<?> upDate(@RequestBody UpdatePostDto updatePostDto){

        PostDto findPostDto = postService.getPostOneByPostId(updatePostDto.getPostId());
        Long userId = findPostDto.getUserId();

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null);

        if(user.getId() != userId){ //검증
            return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(postService.updatePost(updatePostDto));

    }
    @DeleteMapping("/board/post/{postId}")
    @PreAuthorize("hasAnyRole('USER')") //user만 게시글 삭제가능
    public ResponseEntity<?> deletePost(@PathVariable Long postId){

        PostDto findPostDto = postService.getPostOneByPostId(postId);
        Long userId = findPostDto.getUserId();

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null);
        if(user.getId() != userId){ //검증
            return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
        }
        postService.deletePost(postId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}

