package com.reclebooks.recle.controller;


import antlr.Token;
import com.reclebooks.recle.dto.LoginDto;
import com.reclebooks.recle.dto.TokenDto;
import com.reclebooks.recle.dto.UserDto;
import com.reclebooks.recle.jwt.JwtFilter;
import com.reclebooks.recle.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

        private final UserService userService;



        @ApiOperation(value = "회원 가입", notes = "회원 가입 ")
        //userdto로 회원가입 userdto 반환 responseentity로 감싸서 userdto반환
        @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto) {

                return ResponseEntity.ok(userService.signUp(userDto));
        }


        @ApiOperation(value = "로그인", notes = "회원 가입 ")
        //로그인
        @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto){
                TokenDto tokenDto = userService.login(loginDto);

                String jwt = tokenDto.getJwtToken();

                //jwt token 을 response header 에도 넣어주고
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer "+ jwt);

                //Tokendto를 이용해 response response body에도 넣어서 리턴
                return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
        }

        @ApiOperation(value = "회원 정보 조회", notes = "admin 계정만 접근가능 ",produces = MediaType.APPLICATION_JSON_VALUE)
        @GetMapping("/user/{username}")
        @PreAuthorize("hasAnyRole('ADMIN')") //회왼조회 admin 계정만 접근가능
        public ResponseEntity<UserDto> getUserInfo(@PathVariable String username){
                return ResponseEntity.ok(userService.getUserWithAuthorities(username));
        }


}
