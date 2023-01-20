package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findOneWithAuthoritiesByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmailAndPhoneNumberAndName(String email, String phoneNumber, String name);
    Optional<Users> findByEmail(String email);
}
