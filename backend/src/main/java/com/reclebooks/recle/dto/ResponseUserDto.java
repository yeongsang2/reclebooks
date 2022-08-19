package com.reclebooks.recle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reclebooks.recle.domain.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
                .nickname(user.getUsername())
                .authorityDtos(user.getUserAuthorities().stream()
                        .map(userAuthority -> AuthorityDto.builder()
                                .authorityType(userAuthority.getAuthority().getAuthorityType())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return responseUserDto;
    }

}
