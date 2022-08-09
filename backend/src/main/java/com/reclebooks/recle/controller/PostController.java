package com.reclebooks.recle.controller;

import com.reclebooks.recle.dto.GetPostDto;
import com.reclebooks.recle.dto.PostDto;
import com.reclebooks.recle.dto.PostListDto;
import com.reclebooks.recle.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;


    //전체조회
    @GetMapping("/post")
    public ResponseEntity<PostListDto> getPostAll(){
        PostListDto postAll = postService.getPostAll();

        return ResponseEntity.ok(postAll);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostOne(@PathVariable Long postId){
        PostDto findPost = postService.getPostOneByPostId(postId);

        return ResponseEntity.ok(findPost);
    }


}
