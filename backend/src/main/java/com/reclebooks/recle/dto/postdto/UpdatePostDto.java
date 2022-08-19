package com.reclebooks.recle.dto.postdto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePostDto {

    /**
     * 게시글내용과, 책 상태에 관한 정보만
     */

    private Long postId;
    private Long userId;

    private String title;
    private String description;
    private String price;

    private boolean isMarkedWithPencil;
    private boolean isMarkedWithPen;
    private boolean isOutlinedWithPencil;
    private boolean isOutlinedWithPen;
}
