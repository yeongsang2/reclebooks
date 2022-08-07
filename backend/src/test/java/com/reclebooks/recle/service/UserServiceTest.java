package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.domain.UserInfo;
import com.reclebooks.recle.dto.UserDto;
import com.reclebooks.recle.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Test
    @DisplayName("회원가입")
    @Rollback(value = false)
    public void 회원가입(){

        //given
        UserDto userDto = new UserDto();
        userDto.setUsername("yeongsang");
        userDto.setNickname("ys2");
        userDto.setPassword("yeongsang");

        //when

        UserDto userSignUp = userService.signUp(userDto);

        //then

        User user = userRepository.findOneWithuserAuthoritiesByUsername(userSignUp.getUsername()).orElse(null);

        Assertions.assertThat(userSignUp.getUsername()).isEqualTo(user.getUsername());
        System.out.println("user = " + UserDto.from(user));
        System.out.println("userSignUp = " + userSignUp);

    }

}