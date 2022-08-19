package com.reclebooks.recle.dto.postdto;


import com.reclebooks.recle.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter

@Builder
public class ResponseSalesDto {


    private Long postId;
    private String title;
    private String price;
    private boolean isSell;

    public static ResponseSalesDto from(Post post){
        ResponseSalesDto responseSalesDto = ResponseSalesDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .price(post.getPrice())
                .isSell(post.isSell())
                .build();

        return responseSalesDto;
    }

}
