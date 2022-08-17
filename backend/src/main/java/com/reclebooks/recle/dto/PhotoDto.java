package com.reclebooks.recle.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PhotoDto {

    private String photoName;

    private String photoUrl;

    private Long photoSize;

}
