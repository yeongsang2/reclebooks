package com.reclebooks.recle.dto.postdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePostOneDto {
    private PostDto postDto;
    private BytePhotoDto bytePhotoDto;

    public ResponsePostOneDto(PostDto postDto, BytePhotoDto bytePhotoDto) {
        this.postDto = postDto;
        this.bytePhotoDto = bytePhotoDto;
    }

    @Override
    public String toString() {
        return "ResponsePostOneDto{" +
                "postDto=" + postDto +
                ", bytePhotoDto=" + bytePhotoDto +
                '}';
    }
}
