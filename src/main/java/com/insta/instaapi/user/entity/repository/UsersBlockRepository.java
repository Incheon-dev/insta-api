package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.UsersBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersBlockRepository extends JpaRepository<UsersBlock, String> {
    boolean existsByUsersAndBlockedUser(Users users, Users blockedUser);
    List<UsersBlock> findAllByUsers(Users user);
    void deleteByUsersAndBlockedUser(Users users, Users blockedUser);
}
