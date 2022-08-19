package com.reclebooks.recle.dto.postdto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BytePhotoDto {


    private List<byte[]> bytephotos;
    private int cnt;

    public BytePhotoDto(List<byte[]> bytephotos, int cnt) {
        this.bytephotos = bytephotos;
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "BytePhotoDto{" +
                "bytephotos=" + bytephotos.toString() +
                ", cnt=" + cnt +
                '}';
    }
}
