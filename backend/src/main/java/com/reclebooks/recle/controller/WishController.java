package com.reclebooks.recle.controller;


import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.domain.Wish;
import com.reclebooks.recle.dto.wishdto.ResponseWishDto;
import com.reclebooks.recle.dto.wishdto.ResponseWishListDto;
import com.reclebooks.recle.dto.wishdto.WishDto;
import com.reclebooks.recle.service.UserService;
import com.reclebooks.recle.service.WishService;
import com.reclebooks.recle.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class WishController {

    private final UserService userService;
    private final WishService wishService;

    @ApiOperation(value = "찜 하기", notes = "찜 하기 기능",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/wish-list/{postId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> addWishList(@PathVariable Long postId){

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userService.getUserWithAuthorities(username)).get();

        try {
            Long wishId = wishService.addWishList(postId, user.getId());
        } catch (Exception e) {
            return new ResponseEntity<String>("이미 찜함", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ApiOperation(value = "찜 해제", notes = "찜 해제 기능",produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("/wish-list/{postId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> deleteWishList(@PathVariable Long postId){

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userService.getUserWithAuthorities(username)).get();

        try {
            wishService.deleteWishList(postId, user.getId());
        } catch (Exception e) {
            return new ResponseEntity<String>("찜하지 않았음", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ApiOperation(value = "찜 목록 불러오기", notes = "찜 하기 기능",produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/wish-list")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getWishList(){

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userService.getUserWithAuthorities(username)).get();


        List<WishDto> wishList = wishService.getWishList(user.getId());

        return ResponseEntity.ok(new ResponseWishListDto(wishList, wishList.size()));
    }
}
