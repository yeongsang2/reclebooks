package com.reclebooks.recle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePostOneDto {
    PostDto postDto;
    List<byte[]> bytephotos = new ArrayList<>();

    public ResponsePostOneDto(PostDto postDto, List<byte[]> bytephotos) {
        this.postDto = postDto;
        this.bytephotos = bytephotos;
    }

    @Override
    public String toString() {
        return "ResponsePostOneDto{" +
                "postDto=" + postDto +
                ", bytephotos=" + bytephotos.toString() +
                '}';
    }
}
