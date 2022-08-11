package com.reclebooks.recle.controller;


import antlr.Token;
import com.reclebooks.recle.dto.LoginDto;
import com.reclebooks.recle.dto.TokenDto;
import com.reclebooks.recle.dto.UserDto;
import com.reclebooks.recle.jwt.JwtFilter;
import com.reclebooks.recle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

        private final UserService userService;

        //userdto로 회원가입 userdto 반환 responseentity로 감싸서 userdto반환
        @PostMapping("/user")
        public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto) {

                return ResponseEntity.ok(userService.signUp(userDto));
        }


        //로그인
        @PostMapping("/auth")
        public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto){
                TokenDto tokenDto = userService.login(loginDto);

                String jwt = tokenDto.getJwtToken();

                //jwt token 을 response header 에도 넣어주고
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer "+ jwt);

                //Tokendto를 이용해 response response body에도 넣어서 리턴
                return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
        }

        @GetMapping("/user/{username}")
        @PreAuthorize("hasAnyRole('ADMIN')") //회왼조회 admin 계정만 접근가능
        public ResponseEntity<UserDto> getUserInfo(@PathVariable String username){
                return ResponseEntity.ok(userService.getUserWithAuthorities(username));
        }


}
