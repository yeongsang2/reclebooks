package com.reclebooks.recle.repository;

import com.reclebooks.recle.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Optional<Photo>> findAllByPostId(Long postId);

}
