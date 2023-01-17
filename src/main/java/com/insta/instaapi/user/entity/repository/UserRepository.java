package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
