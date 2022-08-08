package com.reclebooks.recle.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.reclebooks.recle.domain.User;
import com.reclebooks.recle.domain.UserInfo;
import lombok.*;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    @Size(min =3 , max =50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min =3 , max =100)
    private String password;

    @NotNull
    @Size(min=3, max=50)
    private String nickname;

    private List<AuthorityDto> authorityDtos = new ArrayList<>();

    public static UserDto from(User user) {
        if(user == null) return null;
        //UserDto 생성
        return UserDto.builder()
                .username(user.getUsername())
                .nickname(user.getUserInfo().getNickName())
                .authorityDtos(user.getUserAuthorities().stream()
                        .map(userAuthority -> AuthorityDto.builder()
                                .userAuthority(userAuthority)
                                .build())
                        .collect(Collectors.toList()))
                .build();

    }
}
