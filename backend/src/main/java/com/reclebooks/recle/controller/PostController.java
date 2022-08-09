package com.reclebooks.recle.controller;

import com.reclebooks.recle.dto.GetPostDto;
import com.reclebooks.recle.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<GetPostDto>> getPostAll(){
        List<GetPostDto> postAll = postService.getPostAll();

        return ResponseEntity.ok(postAll);
    }


}
