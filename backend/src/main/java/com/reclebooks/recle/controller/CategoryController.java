package com.reclebooks.recle.controller;


import com.reclebooks.recle.dto.categorydto.CategoryDto;
import com.reclebooks.recle.dto.categorydto.ResponseCategoryDto;
import com.reclebooks.recle.service.CategoryService;
import com.reclebooks.recle.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation(value = "전체 카테고리 조회", notes = "카테고리 전체 조회")
    @GetMapping("/categorys")
    public ResponseEntity<ResponseCategoryDto> getCategoryAll(){

        List<CategoryDto> categoryDtoAll = categoryService.getCategoryAll().stream()
                .map(category -> CategoryDto.from(category))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ResponseCategoryDto(categoryDtoAll));
    }

    @ApiOperation(value = "자식 카테고리 조회", notes = "해당하는 카테고리의 자식 카테고리 조회")
    @GetMapping("/categorys/{categoryId}")
    public ResponseEntity<ResponseCategoryDto> getCategoryChild(@PathVariable Long categoryId){
        List<CategoryDto> collect = categoryService.getCategoryChild(categoryId).stream()
                .map(category -> CategoryDto.from(category))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ResponseCategoryDto(collect));
    }
}
