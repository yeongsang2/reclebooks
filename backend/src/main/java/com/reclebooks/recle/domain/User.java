package com.reclebooks.recle.domain;


import com.reclebooks.recle.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private boolean activated;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserAuthority> userAuthorities = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> post = new ArrayList<Post>();

//    public static User createUser(UserDto userDto,UserInfo userInfo) {
//        User user = new User();
//        user.userInfo = userInfo;
//        user.setUsername(userDto.getUsername());
//        user.setPassword(passwordEuserDto.getPassword());
//        user.setActivated(true);
//        return user;
//
//    }

}

