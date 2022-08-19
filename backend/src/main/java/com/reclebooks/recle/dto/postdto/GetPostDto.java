package com.reclebooks.recle.dto.postdto;

import com.reclebooks.recle.domain.Post;
import lombok.Getter;
import lombok.Setter;


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

    private int viewCount;

    private String location;

    private byte[] image;
    public static GetPostDto from(Post post, byte[] imageByteArray){

        GetPostDto getPostDto = new GetPostDto();

        getPostDto.setUserId(post.getUser().getId());
        getPostDto.setPostId(post.getId());
        getPostDto.setTitle(post.getTitle());
        getPostDto.setPrice(post.getPrice());
        getPostDto.setBookName(post.getBook().getName());
        getPostDto.setViewCount(post.getViewCount());
        getPostDto.setLocation(post.getLocation());

        getPostDto.setImage(imageByteArray);

        return getPostDto;
    }

}