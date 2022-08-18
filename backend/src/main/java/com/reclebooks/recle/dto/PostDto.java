package com.reclebooks.recle.dto;

import com.reclebooks.recle.domain.Category;
import com.reclebooks.recle.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PostDto {


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


    public static PostDto from(Post post){
        
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setPrice(post.getPrice());
        postDto.setUserId(post.getUser().getId());
        postDto.setViewCount(post.getViewCount());
        
        postDto.setCategoryDtos(post.getPostCategories().stream()
                        .map(postCategory -> new CategoryDto(postCategory.getCategory().getId(), postCategory.getCategory().getName()))
                        .collect(Collectors.toList()));

        postDto.setBookName(post.getBook().getName());
        postDto.setBookAuthor(post.getBook().getAuthor());
        postDto.setBookPublisher(post.getBook().getPublisher());
        postDto.setPublishDate(post.getBook().getPublishDate());
        postDto.setBookPrice(post.getBook().getPrice());
        postDto.setIsbn(post.getBook().getIsbn());

        postDto.setMarkedWithPencil(post.getBookState().isMarkedWithPencil());
        postDto.setMarkedWithPen(post.getBookState().isMarkedWithPen());
        postDto.setOutlinedWithPencil(post.getBookState().isOutlinedWithPencil());
        postDto.setOutlinedWithPen(post.getBookState().isOutlinedWithPen());

        return postDto;
    }


    @Override
    public String toString() {
        return "PostDto{" +
                "userId=" + userId +
                ", PostId=" + PostId +
                ", categoryDtos=" + categoryDtos +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublisher='" + bookPublisher + '\'' +
                ", bookPrice='" + bookPrice + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", isMarkedWithPencil=" + isMarkedWithPencil +
                ", isMarkedWithPen=" + isMarkedWithPen +
                ", isOutlinedWithPencil=" + isOutlinedWithPencil +
                ", isOutlinedWithPen=" + isOutlinedWithPen +
                '}';
    }
}
