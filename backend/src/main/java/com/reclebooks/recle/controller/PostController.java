package com.reclebooks.recle.controller;

import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.dto.*;
import com.reclebooks.recle.repository.UserRepository;
import com.reclebooks.recle.service.CategoryService;
import com.reclebooks.recle.service.PhotoService;
import com.reclebooks.recle.service.PhotoServiceImpl;
import com.reclebooks.recle.service.PostService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;


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
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    private final PhotoService photoService;

    //전체조회 --> 추후 paging 개선
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록 전체 조회")
    @GetMapping(value = "/board", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostListDto> getPostAll() throws IOException {

        PostListDto postAll = postService.getPostAll();

        return ResponseEntity.ok(postAll);
    }


    // 단건조회
    @ApiOperation(value = "게시글 조회", notes = "게시글 하나 조회")
    @GetMapping(value = "/board/post/{postId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponsePostOneDto> getPostOne(@PathVariable Long postId) throws IOException {

        //조회수 증가
        postService.addViewCount(postId);

        PostDto findPost = postService.getPostOneByPostId(postId);

        List<byte[]> bytephotos = photoService.getPhotos(postId);

        BytePhotoDto bytePhotoDto = BytePhotoDto.builder()
                .bytephotos(bytephotos)
                .cnt(bytephotos.size())
                .build();

        ResponsePostOneDto responsePostOneDto = new ResponsePostOneDto(findPost,bytePhotoDto);

        return ResponseEntity.ok(responsePostOneDto);
    }


    @ApiOperation(value = "게시글 등록", notes = "게시글 등록")
    @PostMapping(value = "/board/post" ,  consumes = { MediaType.APPLICATION_JSON_VALUE,  MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("hasAnyRole('USER')") //user만 게시글 작성가능
    public ResponseEntity<Long> createPost(@RequestPart PostDto postDto, @RequestPart(required = false) List<MultipartFile> files) throws Exception {

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null);

        postDto.setUserId(user.getId());

        return ResponseEntity.ok(postService.createPost(postDto, files));
    }

    //추후 수정
    @ApiOperation(value = "게시글 수정", notes = "도서상태, 게시글 내용만 수정가능 ")
    @PatchMapping("/board/post")
    @PreAuthorize("hasAnyRole('USER')") // 해당 사용자만 게시글 수정 가능
    public ResponseEntity<?> upDate(@RequestBody UpdatePostDto updatePostDto){

        PostDto findPostDto = postService.getPostOneByPostId(updatePostDto.getPostId());
        Long userId = findPostDto.getUserId();

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null);

        if(user.getId() != userId){ //검증
            return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(postService.updatePost(updatePostDto));

    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제")
    @DeleteMapping("/board/post/{postId}")
    @PreAuthorize("hasAnyRole('USER')") //user만 게시글 삭제가능
    public ResponseEntity<?> deletePost(@PathVariable Long postId){

        PostDto findPostDto = postService.getPostOneByPostId(postId);
        Long userId = findPostDto.getUserId();

        User user = SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null);
        if(user.getId() != userId){ //검증
            return new ResponseEntity<String>("Forbidden", HttpStatus.FORBIDDEN);
        }
        postService.deletePost(postId);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}

