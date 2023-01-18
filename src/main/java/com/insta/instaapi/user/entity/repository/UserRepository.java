package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findOneWithAuthoritiesByUsername(String username);
    boolean existsByEmail(String email);
}
