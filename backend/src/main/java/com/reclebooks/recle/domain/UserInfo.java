package com.reclebooks.recle.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserInfo {

    @Id
    @GeneratedValue
    @Column(name = "user_info_id")
    private Long id;

    private String nickName;
    private String position;
    private String school;
    private int reputationScore;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_interest_id")
    private UserInterest userInterest;


}
