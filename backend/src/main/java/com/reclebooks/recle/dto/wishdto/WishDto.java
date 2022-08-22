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


    @Override
    public String toString() {
        return "WishDto{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", isSell=" + isSell +
                '}';
    }
}
