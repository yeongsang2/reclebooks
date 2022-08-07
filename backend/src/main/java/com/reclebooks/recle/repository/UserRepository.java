package com.reclebooks.recle.repository;

import com.reclebooks.recle.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "userAuthorities")
    Optional<User> findOneWithuserAuthoritiesByUsername(String username);
}
