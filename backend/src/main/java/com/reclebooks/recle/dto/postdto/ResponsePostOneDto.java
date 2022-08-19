package com.reclebooks.recle.dto.postdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePostOneDto {
    private ResponsePostDto responsePostDto;
    private BytePhotoDto bytePhotoDto;

    public ResponsePostOneDto(ResponsePostDto responsePostDto, BytePhotoDto bytePhotoDto) {
        this.responsePostDto = responsePostDto;
        this.bytePhotoDto = bytePhotoDto;
    }

    @Override
    public String toString() {
        return "ResponsePostOneDto{" +
                "responsePostDto=" + responsePostDto +
                ", bytePhotoDto=" + bytePhotoDto +
                '}';
    }
}
