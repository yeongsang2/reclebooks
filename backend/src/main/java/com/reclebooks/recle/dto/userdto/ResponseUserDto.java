package com.reclebooks.recle.dto.userdto;

import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.dto.authdto.AuthorityDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@Getter
public class ResponseUserDto {

    private String username;

    private String nickname;

    private List<AuthorityDto> authorityDtos = new ArrayList<>();


    public static ResponseUserDto from(User user){

        ResponseUserDto responseUserDto = ResponseUserDto.builder()
                .username(user.getUsername())
                .nickname(user.getUserInfo().getNickName())
                .authorityDtos(user.getUserAuthorities().stream()
                        .map(userAuthority -> AuthorityDto.builder()
                                .authorityType(userAuthority.getAuthority().getAuthorityType())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return responseUserDto;
    }

}
