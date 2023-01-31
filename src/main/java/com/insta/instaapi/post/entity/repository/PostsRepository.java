package com.insta.instaapi.post.entity.repository;

import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, String>  {

    List<Posts> findByUsers(Users user);
    Long countByUsers(Users user);
}