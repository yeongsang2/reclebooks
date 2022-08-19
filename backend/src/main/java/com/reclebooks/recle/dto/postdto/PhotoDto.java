package com.reclebooks.recle.dto.postdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PhotoDto {

    private String photoName;

    private String photoPath;

    private Long photoSize;

}
