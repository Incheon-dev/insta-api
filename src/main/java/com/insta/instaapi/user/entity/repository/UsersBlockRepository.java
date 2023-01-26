package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.UsersBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersBlockRepository extends JpaRepository<UsersBlock, String> {
    boolean existsByUsersAndOtherUserId(Users users, String otherUserId);
}
