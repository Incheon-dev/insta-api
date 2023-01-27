package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.UsersFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersFollowRepository extends JpaRepository<UsersFollow, String> {
}
