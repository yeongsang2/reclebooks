package com.reclebooks.recle.domain;

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

    @OneToMany(mappedBy = "post")
    private List<File> fileList = new ArrayList<File>();

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne
    @JoinColumn(name = "book_state_id")
    private BookState bookState;

    @OneToMany(mappedBy = "post")
    private List<PostCategory> postCategories;

    private String title;
    private String description;
    private String price;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
