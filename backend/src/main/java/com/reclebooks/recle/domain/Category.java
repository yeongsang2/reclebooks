package com.reclebooks.recle.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // 내부모니까 many TO One
    @JoinColumn(name="parent_id")
    private Category parent;


    @OneToMany(mappedBy = "category")
    private List<PostCategory> postCategories;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // 양방향 편의 메소드
    public void addChildCategory(Category child){

        this.child.add(child); // 자식 추가
        child.setParent(this); // 부모 설정

    }
}
