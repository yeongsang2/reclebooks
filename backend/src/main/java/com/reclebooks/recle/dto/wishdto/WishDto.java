package com.reclebooks.recle.dto.wishdto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WishDto {


    private Long postId;
    private String title;
    private String price;
    private boolean isSell;


}
