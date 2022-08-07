package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.*;
import com.reclebooks.recle.dto.UserDto;
import com.reclebooks.recle.repository.AuthorityRepository;
import com.reclebooks.recle.repository.UserRepository;
import com.reclebooks.recle.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {


    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto signUp(UserDto userdto) {
        if (userRepository.findOneWithuserAuthoritiesByUsername(userdto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저 입니다.");
        }
        //userInfo null NullPointerException
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(userdto.getNickname());

        User user = User.builder()
                .username(userdto.getUsername())
                .password(passwordEncoder.encode(userdto.getPassword()))
                .userInfo(userInfo)
                .activated(true)
                .build();

        user.setUserAuthorities(new ArrayList<UserAuthority>());

        Authority authority = new Authority();
        authority.setAuthorityType(AuthorityType.ROlE_USER);

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setAuthority(authority);
        userAuthority.setUser(user);
        userAuthority.getUser().getUserAuthorities().add(userAuthority);

        return UserDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    //username을 기준으로 정보를 가져옴
    public UserDto getUserWithAuthorities(String username){
        return UserDto.from(userRepository.findOneWithuserAuthoritiesByUsername(username).orElse(null));
    }

    //SecurityContext에 저장된 username의 정보만 가져옴옴
    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities(){
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null));

    }

}
