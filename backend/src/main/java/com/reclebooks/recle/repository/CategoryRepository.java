package com.reclebooks.recle.repository;

import com.reclebooks.recle.domain.Category;
import com.reclebooks.recle.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findOneByName(String categoryName);
}
