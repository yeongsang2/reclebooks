package com.reclebooks.recle.repository;

import com.reclebooks.recle.domain.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

    List<Optional<PostCategory>> findAllByCategoryId(Long categoryId);
}
