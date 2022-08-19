package com.reclebooks.recle.dto.authdto;

import com.reclebooks.recle.domain.AuthorityType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto {

    private AuthorityType authorityType;
}
