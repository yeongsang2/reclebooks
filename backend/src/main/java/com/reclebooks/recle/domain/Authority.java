package com.reclebooks.recle.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType;

    @OneToMany(mappedBy = "authority")
    private List<UserAuthority> userAuthorityList;
}
