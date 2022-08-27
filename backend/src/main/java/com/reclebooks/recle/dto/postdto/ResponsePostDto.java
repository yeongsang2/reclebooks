package com.reclebooks.recle.dto.postdto;

import com.reclebooks.recle.domain.Post;
import com.reclebooks.recle.dto.categorydto.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 응답용 postDto
 */
@Getter
@Setter
public class ResponsePostDto {

    private Long userId;
    private Long PostId;
    private List<CategoryDto> categoryDtos = new ArrayList<>();

    private String title;
    private String description;
    private String price;

    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private String bookPrice;
    private String isbn;
    private String publishDate;

    private boolean isMarkedWithPencil;
    private boolean isMarkedWithPen;
    private boolean isOutlinedWithPencil;
    private boolean isOutlinedWithPen;

    private int viewCount;

    private String location;

    private int wishCount;

    public static ResponsePostDto from(Post post){

        ResponsePostDto ResponsePostDto = new ResponsePostDto();
        ResponsePostDto.setPostId(post.getId());
        ResponsePostDto.setTitle(post.getTitle());
        ResponsePostDto.setDescription(post.getDescription());
        ResponsePostDto.setPrice(post.getPrice());
        ResponsePostDto.setUserId(post.getUser().getId());
        ResponsePostDto.setViewCount(post.getViewCount());
        ResponsePostDto.setLocation(post.getLocation());
        ResponsePostDto.setWishCount(post.getWishList().size());

        ResponsePostDto.setCategoryDtos(post.getPostCategories().stream()
                .map(postCategory -> new CategoryDto(postCategory.getCategory().getId(), postCategory.getCategory().getName()))
                .collect(Collectors.toList()));

        ResponsePostDto.setBookName(post.getBook().getTitle());
        ResponsePostDto.setBookAuthor(post.getBook().getAuthor());
        ResponsePostDto.setBookPublisher(post.getBook().getPublisher());
        ResponsePostDto.setPublishDate(post.getBook().getPubdate());
        ResponsePostDto.setBookPrice(post.getBook().getPrice());
        ResponsePostDto.setIsbn(post.getBook().getIsbn());

        ResponsePostDto.setMarkedWithPencil(post.getBookState().isMarkedWithPencil());
        ResponsePostDto.setMarkedWithPen(post.getBookState().isMarkedWithPen());
        ResponsePostDto.setOutlinedWithPencil(post.getBookState().isOutlinedWithPencil());
        ResponsePostDto.setOutlinedWithPen(post.getBookState().isOutlinedWithPen());

        return ResponsePostDto;
    }
}
