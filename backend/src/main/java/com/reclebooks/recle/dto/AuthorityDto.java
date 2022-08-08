package com.reclebooks.recle.dto;

import com.reclebooks.recle.domain.Authority;
import com.reclebooks.recle.domain.AuthorityType;
import com.reclebooks.recle.domain.UserAuthority;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto2 {

    private AuthorityType authorityType;
}
