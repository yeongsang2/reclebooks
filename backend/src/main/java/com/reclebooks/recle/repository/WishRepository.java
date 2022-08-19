package com.reclebooks.recle.repository;

import com.reclebooks.recle.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
