package com.reclebooks.recle.repository;

import com.reclebooks.recle.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {

    public Optional<Wish> findByPostIdAndUserId(Long postId, Long userId);
}
