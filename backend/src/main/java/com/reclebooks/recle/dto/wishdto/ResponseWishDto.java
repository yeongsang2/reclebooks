package com.reclebooks.recle.dto.wishdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ResponseWishDto {

    private Long postId;
    private String title;
    private String price;
    private boolean isSell;




}
