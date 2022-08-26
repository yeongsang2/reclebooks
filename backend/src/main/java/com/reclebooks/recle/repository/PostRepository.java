package com.reclebooks.recle.repository;

import com.reclebooks.recle.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

        List<Post> findAllByTitleContaining(String title);
}
