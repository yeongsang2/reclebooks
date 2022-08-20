package com.reclebooks.recle.dto.postdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostListDto {
    /**
     * 전체게시글 조회
     */
    private int count;
    private List<GetPostDto> postDtos = new ArrayList<>();

}
