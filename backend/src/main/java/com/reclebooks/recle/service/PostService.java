package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Post;
import com.reclebooks.recle.dto.PostDto;
import com.reclebooks.recle.dto.getPostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostService{


    // 전체조회
    public List<getPostDto> getPostAll();

    // 단건조회
    public PostDto getPostOneByPostId(Long id);

    // 등록
    public Long createPost(PostDto postDto);
    // 수정
    public Long updatePost(PostDto postDto);
    // 삭제
    public Long deletePost(PostDto postDto);

    // 검색
}
