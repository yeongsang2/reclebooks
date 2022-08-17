package com.reclebooks.recle.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    private String photoName;

    private String photoPath;

    private Long photoSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post){
        post.getPhotoList().add(this);
        this.post = post;
    }
}
