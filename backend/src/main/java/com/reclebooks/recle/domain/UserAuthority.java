package com.reclebooks.recle.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthority {

    @Id
    @GeneratedValue
    @Column(name = "user_authority_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name="authority_id")
    private Authority authority;

//    public static UserAuthority createUserAuthority(User user){
//        UserAuthority userAuthority = new UserAuthority();
//
//        Authority authority = new Authority();
//        authority.setAuthorityType(AuthorityType.ROlE_USER);
//
//        userAuthority.setUser(user);
//        user.getUserAuthorities().add(userAuthority); //연관관계 편의메소드?
//
//        return userAuthority;
//    }
}
