package com.reclebooks.recle.domain;

import com.reclebooks.recle.dto.postdto.PostDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class BookState {

    @Id
    @GeneratedValue
    @Column(name = "book_state_id")
    private Long id;

    private boolean isMarkedWithPencil;
    private boolean isMarkedWithPen;
    private boolean isOutlinedWithPencil;
    private boolean isOutlinedWithPen;

    public static BookState createBookState(PostDto postDto){

        BookState bookState = new BookState();
        bookState.setMarkedWithPen(postDto.isMarkedWithPen());
        bookState.setMarkedWithPencil(postDto.isMarkedWithPen());
        bookState.setOutlinedWithPencil(postDto.isOutlinedWithPencil());
        bookState.setMarkedWithPen(postDto.isOutlinedWithPen());

        return bookState;
    }

}
