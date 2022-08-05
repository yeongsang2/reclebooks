package com.reclebooks.recle.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter
public class Role {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

}
