package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.UsersBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersBlockRepository extends JpaRepository<UsersBlock, String> {
    boolean existsByUsersAndOtherUserId(Users users, String otherUserId);
    List<UsersBlock> findAllByUsers(Users users);
    void deleteByUsersAndOtherUserId(Users users, String otherUserId);
}
