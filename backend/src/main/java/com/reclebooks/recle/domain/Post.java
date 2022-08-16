package com.reclebooks.recle.domain;

import com.reclebooks.recle.dto.PostDto;
import com.reclebooks.recle.dto.UpdatePostDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {


    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Photo> photoList = new ArrayList<Photo>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_state_id")
    private BookState bookState;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostCategory> postCategories = new ArrayList<>();

    private String title;
    private String description;
    private String price;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static Post createPost(PostDto postDto, User user, Book book,BookState bookState){

        Post post = new Post();

        post.setUser(user);

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());

        post.setPrice(postDto.getPrice());
        post.setCreateDate(LocalDateTime.now());

        post.setBook(book);
        post.setBookState(bookState);

        return post;

    }

    public Post updatePost(UpdatePostDto updatePostDto){
        title = updatePostDto.getTitle();
        description = updatePostDto.getDescription();
        price = updatePostDto.getPrice();
        updateDate = LocalDateTime.now();

        bookState.setMarkedWithPen(updatePostDto.isMarkedWithPen());
        bookState.setMarkedWithPencil(updatePostDto.isMarkedWithPencil());
        bookState.setOutlinedWithPencil(updatePostDto.isOutlinedWithPencil());
        bookState.setOutlinedWithPen(updatePostDto.isOutlinedWithPen());

        return this;
    }

    public void addPostCategory(PostCategory postCategory){
        postCategories.add(postCategory);
        postCategory.setPost(this);
    }

}
