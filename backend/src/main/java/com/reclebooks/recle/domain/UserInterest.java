package com.reclebooks.recle.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class UserInterest {

    @Id
    @GeneratedValue
    @Column(name = "user_interest_id")
    private Long id;

    private String interest;

}

