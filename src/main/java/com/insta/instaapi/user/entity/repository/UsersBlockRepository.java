package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.UsersBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersBlockRepository extends JpaRepository<UsersBlock, String> {
    boolean existsByUsersAndOtherUserId(Users users, String otherUserId);
    List<UsersBlock> findAllByUsers(Users users);
    void deleteByUsersAndOtherUserId(Users users, String otherUserId);
}
