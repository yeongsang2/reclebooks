package com.reclebooks.recle.vo;


import com.reclebooks.recle.dto.CategoryDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostFileVo {

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

    private List<MultipartFile> files;

}
