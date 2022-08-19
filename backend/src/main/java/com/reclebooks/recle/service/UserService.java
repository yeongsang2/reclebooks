package com.reclebooks.recle.service;

import com.reclebooks.recle.domain.*;
import com.reclebooks.recle.dto.userdto.LoginDto;
import com.reclebooks.recle.dto.userdto.ResponseUserDto;
import com.reclebooks.recle.dto.authdto.TokenDto;
import com.reclebooks.recle.dto.userdto.UserDto;
import com.reclebooks.recle.jwt.TokenProvider;
import com.reclebooks.recle.repository.UserRepository;
import com.reclebooks.recle.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    public UserDto signUp(UserDto userdto) {
        if (userRepository.findOneWithuserAuthoritiesByUsername(userdto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저 입니다.");
        }
        //userInfo null NullPointerException
        UserInfo userInfo = createUserInfo(userdto);
        User user = User.builder()
                .username(userdto.getUsername())
                .password(passwordEncoder.encode(userdto.getPassword()))
                .userInfo(userInfo)
                .activated(true)
                .build();

        user.setUserAuthorities(new ArrayList<UserAuthority>());
        Authority authority = createAuthority();
        createUserAuthority(user, authority);

        return UserDto.from(userRepository.save(user));
    }


    public TokenDto login(LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        // authenticationtoken을 이용해서 authentication객체를 생성하려고     authenticate 메소드가 실행될때 loadUserByUsername 메소드 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // authentication 객체를 생성하고 이를 SecurityContext에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //authentication 객체를  createToken 메소드를 통해 JWT Token 생성
        String jwt = tokenProvider.createToken(authentication);
        return new TokenDto(jwt);
    }


    @Transactional(readOnly = true)
    //username을 기준으로 정보를 가져옴
    public ResponseUserDto getUserWithAuthorities(String username){
        return ResponseUserDto.from(userRepository.findOneWithuserAuthoritiesByUsername(username).orElse(null));
    }

    //SecurityContext에 저장된 username의 정보만 가져옴옴
    @Transactional(readOnly = true)
    public User getMyUserWithAuthorities(){

        return SecurityUtil.getCurrentUsername().flatMap(username -> userRepository.findOneWithuserAuthoritiesByUsername(username)).orElse(null);
    }
    private UserInfo createUserInfo(UserDto userdto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(userdto.getNickname());
        return userInfo;
    }

    private Authority createAuthority() {
        Authority authority = new Authority();
        authority.setAuthorityType(AuthorityType.ROLE_USER);
        return authority;
    }

    private void createUserAuthority(User user, Authority authority) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setAuthority(authority);
        userAuthority.setUser(user);
        userAuthority.getUser().getUserAuthorities().add(userAuthority);
    }

}
