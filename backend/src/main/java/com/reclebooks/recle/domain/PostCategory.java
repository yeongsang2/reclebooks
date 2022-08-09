package com.reclebooks.recle.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class PostCategory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setPost(Post post) {
        this.post = post;
        //편의메소드
        post.getPostCategories().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getPostCategories().add(this);
    }
}
