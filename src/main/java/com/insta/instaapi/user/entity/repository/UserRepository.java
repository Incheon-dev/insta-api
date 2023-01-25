package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findOneWithAuthoritiesByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmailAndPhoneNumberAndName(String email, String phoneNumber, String name);
    Optional<Users> findByEmail(String email);
    void deleteByEmail(String email);
}
