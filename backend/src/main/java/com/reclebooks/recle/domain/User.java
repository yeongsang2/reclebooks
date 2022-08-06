package com.reclebooks.recle.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

    @OneToOne
    private Authority authority;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user")
    private List<Post> post = new ArrayList<Post>();

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

}
