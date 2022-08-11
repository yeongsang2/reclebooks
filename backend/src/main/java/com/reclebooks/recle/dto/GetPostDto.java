package com.reclebooks.recle.dto;

import com.reclebooks.recle.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;


@Getter @Setter
public class GetPostDto {

    /**
     * 전체조회 dto
     */
    private Long userId;
    private Long PostId;

    private String title;
    private String price;

    private String bookName;
    public static GetPostDto from(Post post){

        GetPostDto getPostDto = new GetPostDto();

        getPostDto.setUserId(post.getUser().getId());
        getPostDto.setPostId(post.getId());
        getPostDto.setTitle(post.getTitle());
        getPostDto.setPrice(post.getPrice());
        getPostDto.setBookName(post.getBook().getName());

        return getPostDto;
    }

    @Override
    public String toString() {
        return "GetPostDto{" +
                "userId=" + userId +
                ", PostId=" + PostId +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
