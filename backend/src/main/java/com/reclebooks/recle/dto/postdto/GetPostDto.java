package com.reclebooks.recle.dto.postdto;

import com.reclebooks.recle.domain.Post;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class GetPostDto {

    /**
     * 게시글 전체조회 할때 사용하는 dto
     */
    private Long userId;
    private Long PostId;

    private String title;
    private String price;

    private String bookName;

    private int viewCount;

    private String location;

    private boolean isSell;

    private String thumbnailImage;

    private int wishCount;
    public static GetPostDto from(Post post){

        GetPostDto getPostDto = new GetPostDto();

        getPostDto.setUserId(post.getUser().getId());
        getPostDto.setPostId(post.getId());
        getPostDto.setTitle(post.getTitle());
        getPostDto.setPrice(post.getPrice());
        getPostDto.setBookName(post.getBook().getTitle());
        getPostDto.setViewCount(post.getViewCount());
        getPostDto.setLocation(post.getLocation());
        getPostDto.setSell(post.isSell());
        getPostDto.setWishCount(post.getWishList().size());

        getPostDto.setThumbnailImage(post.getBook().getThumbnailImage());

        return getPostDto;
    }

}
