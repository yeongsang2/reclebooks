package com.reclebooks.recle.controller;

import com.reclebooks.recle.dto.bookdto.SearchBookDto;
import com.reclebooks.recle.dto.postdto.PostListDto;
import com.reclebooks.recle.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
public class BookController {


    private final BookService bookService;

    @ApiOperation(value = "책 검색", notes = "책 검색 , keyword param")
    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchBookDto> searchBook(@RequestParam(required = false) String keyword) {

        SearchBookDto searchBookDto = bookService.searchBook(keyword);

        return ResponseEntity.ok(searchBookDto);
    }


}
