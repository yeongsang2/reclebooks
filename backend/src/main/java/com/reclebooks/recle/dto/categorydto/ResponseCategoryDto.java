package com.reclebooks.recle.dto.categorydto;

import lombok.Getter;

import java.util.List;


@Getter
public class ResponseCategoryDto {

    private List<CategoryDto> categoryAll;

    public ResponseCategoryDto(List<CategoryDto> categoryAll) {
        this.categoryAll = categoryAll;
    }
}
