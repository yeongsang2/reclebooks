package com.reclebooks.recle.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user")
    private List<Post> post = new ArrayList<Post>();

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

}
