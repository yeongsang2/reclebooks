package com.reclebooks.recle.service;

import com.reclebooks.recle.dto.PostDto;
import com.reclebooks.recle.dto.GetPostDto;
import com.reclebooks.recle.dto.PostListDto;
import com.reclebooks.recle.dto.UpdatePostDto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PostService{


    // 전체조회
    public PostListDto getPostAll();

    // 단건조회
    public PostDto getPostOneByPostId(Long id);

    // 등록
    public Long createPost(PostDto postDto, List<MultipartFile> files) throws Exception;
    // 수정
    public Long updatePost(UpdatePostDto updatePostDto);
    // 삭제
    public void deletePost(Long postId);

    // 검색
}
