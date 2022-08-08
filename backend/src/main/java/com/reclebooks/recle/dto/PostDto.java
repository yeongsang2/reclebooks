package com.reclebooks.recle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDto {


    private Long userId;

    private Long categoryId;

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

}
