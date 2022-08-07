package com.reclebooks.recle.service;


import com.reclebooks.recle.domain.Authority;
import com.reclebooks.recle.domain.AuthorityType;
import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    // 로그인시에 db에서 유저정보와 권한정보를 가져옴 해당 정보를 기반으로 userDetails.User 객체 생성
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneWithuserAuthoritiesByUsername(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없음"));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
        if (!user.isActivated()){
            throw new RuntimeException(username + " -> 활성화 되어 있지 않음");
        }


        List<SimpleGrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
                .map(userAuthority -> new SimpleGrantedAuthority(userAuthority.getAuthority().getAuthorityType().toString()))
                .collect(Collectors.toList());
//        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
//                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}
