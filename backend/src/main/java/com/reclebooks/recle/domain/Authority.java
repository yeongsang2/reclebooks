package com.reclebooks.recle.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Authority {

    @Id @GeneratedValue

    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType;
}
