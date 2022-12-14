//package com.reclebooks.recle.service;
//
//import com.reclebooks.recle.dto.userdto.LoginDto;
//import com.reclebooks.recle.dto.authdto.TokenDto;
//import com.reclebooks.recle.dto.userdto.UserDto;
//import com.reclebooks.recle.repository.UserRepository;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Test
//    @DisplayName("회원가입")
//    @Rollback(value = false)
//    public void 회원가입(){
//
//        //given
//        UserDto userDto = new UserDto();
//        userDto.setUsername("yeongsang");
//        userDto.setNickname("ys2");
//        userDto.setPassword("yeongsang");
//
//        //when
//        System.out.println("===========================================");
//        UserDto userSignUp = userService.signUp(userDto);
//        System.out.println("name: " + userSignUp.getUsername());
//        System.out.println("nickname: " +userSignUp.getNickname());
////        List<AuthorityDto> authorityDtos = userSignUp.getAuthorityDtos();
////        for (AuthorityDto authorityDto : authorityDtos) {
////            System.out.println("authorityDto = " + authorityDto.getUserAuthority().getAuthority());
////        }
////        System.out.println("password: " + userSignUp.getPassword());
////        //then
////        UserDto userDto1 = UserDto.from(userRepository.findOneWithuserAuthoritiesByUsername(userSignUp.getUsername()).orElse(null));
////        Assertions.assertThat(userSignUp.getUsername()).isEqualTo(userDto1.getUsername());
////        Assertions.assertThat(userSignUp.getNickname()).isEqualTo(userDto1.getNickname());
////        System.out.println("user = " + UserDto.from(user));
////        System.out.println("userSignUp = " + userSignUp);
//
//    }
//
//    @Test
//    @DisplayName("로그인")
//    @Rollback(value = false)
//    public void 로그인(){
//        //given
//        UserDto userDto = new UserDto();
//        userDto.setUsername("yeongsang");
//        userDto.setNickname("ys2");
//        userDto.setPassword("yeongsang");
//
//        UserDto userSignUp = userService.signUp(userDto);
//        LoginDto loginDto = new LoginDto("yeongsang","yeongsang");
//
//        //when
//
//
//        TokenDto token = userService.login(loginDto);
//        org.junit.jupiter.api.Assertions.assertNotNull(token.getJwtToken());
//        System.out.println("token.getToken() = " + token.getJwtToken());
//    }
//
//
//
//}