package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.Post;
import com.reclebooks.recle.dto.postdto.PostDto;
import com.reclebooks.recle.dto.postdto.PostListDto;
import com.reclebooks.recle.dto.postdto.UpdatePostDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface PostService{


    // 전체조회
    public PostListDto getPostAll() throws IOException;

    // 단건조회
    public Post getPostOneByPostId(Long id);

    // 등록
    public Long createPost(PostDto postDto, List<MultipartFile> files) throws Exception;
    // 수정
    public Long updatePost(UpdatePostDto updatePostDto);
    // 삭제
    public void deletePost(Long postId);

    //조회수 증가
    public void addViewCount(Long postId);

    // 판매완료
    public void salesComplete(Long postId);

    //카테고리 필터
    public PostListDto getPostFilterByCategory(Long categoryId) throws IOException;


    //
    // 검색
}
