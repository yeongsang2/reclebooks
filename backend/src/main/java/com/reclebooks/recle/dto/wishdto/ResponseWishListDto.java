package com.reclebooks.recle.dto.wishdto;


import com.reclebooks.recle.domain.Wish;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseWishListDto {


    private List<WishDto> wishDtoList = new ArrayList<>();
    private int count;

    public ResponseWishListDto(List<WishDto> wishDtoList, int count) {
        this.wishDtoList = wishDtoList;
        this.count = count;
    }

    @Override
    public String toString() {
        return "ResponseWishListDto{" +
                "wishDtoList=" + wishDtoList +
                ", count=" + count +
                '}';
    }
}
