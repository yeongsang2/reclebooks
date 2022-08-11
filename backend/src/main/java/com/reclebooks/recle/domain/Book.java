package com.reclebooks.recle.domain;

import com.reclebooks.recle.dto.PostDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name;
    private String author;
    private String price;
    private String isbn;
    private String publisher;
    private String publishDate;

    public static Book createBook(PostDto postDto){

        Book book = new Book();
        book.setName(postDto.getBookName());
        book.setPrice(postDto.getPrice());
        book.setAuthor(postDto.getBookAuthor());
        book.setIsbn(postDto.getIsbn());
        book.setPublisher(postDto.getBookPublisher());
        book.setPublishDate(postDto.getPublishDate());

        return book;

    }

}
