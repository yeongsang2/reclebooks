package com.reclebooks.recle.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostListDto {
    // 전체조회
    private int count;
    private List<GetPostDto> postDtos = new ArrayList<>();

}
