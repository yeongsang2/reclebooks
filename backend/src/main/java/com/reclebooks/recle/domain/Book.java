package com.reclebooks.recle.domain;

import com.reclebooks.recle.dto.bookdto.SearchBookDtoByIsbn;
import com.reclebooks.recle.dto.postdto.PostDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String title;
    private String link;
    private String thumbnailImage;
    private String author;
    private String price;
    private String publisher;
    private String pubdate;
    private String isbn;

    public static Book createBook(SearchBookDtoByIsbn searchBookDtoByIsbn){

        Book book = new Book();


        book.setTitle(searchBookDtoByIsbn.getChannel().getItem().getTitle());
        book.setLink(searchBookDtoByIsbn.getChannel().getItem().getLink());
        book.setThumbnailImage(searchBookDtoByIsbn.getChannel().getItem().getImage());
        book.setAuthor(searchBookDtoByIsbn.getChannel().getItem().getAuthor());
        book.setPrice(searchBookDtoByIsbn.getChannel().getItem().getPrice());
        book.setPublisher(searchBookDtoByIsbn.getChannel().getItem().getPublisher());
        book.setPubdate(searchBookDtoByIsbn.getChannel().getItem().getPubdate());
        book.setIsbn(searchBookDtoByIsbn.getChannel().getItem().getAuthor());

        return book;
    }

}
