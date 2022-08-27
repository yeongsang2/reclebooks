package com.reclebooks.recle.dto.postdto;

import com.reclebooks.recle.domain.Post;
import com.reclebooks.recle.dto.categorydto.CategoryDto;
import lombok.Getter;
import lombok.Setter;

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

    private String isbn;

    private boolean isMarkedWithPencil;
    private boolean isMarkedWithPen;
    private boolean isOutlinedWithPencil;
    private boolean isOutlinedWithPen;

    private String location;

}
