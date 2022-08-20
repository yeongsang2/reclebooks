package com.reclebooks.recle.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Wish {

    @Id @GeneratedValue
    @Column(name = "wish_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    public void setUser(User user){
        this.user = user;
        user.getWishList().add(this);
    }
    public void setPost(Post post){
        this.post = post;
        post.getWishList().add(this);
    }

}
