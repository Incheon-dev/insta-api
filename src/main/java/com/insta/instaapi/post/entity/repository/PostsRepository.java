package com.insta.instaapi.post.entity.repository;

import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.post.entity.PostsStatus;
import com.insta.instaapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, String>  {

    List<Posts> findByUsersAndPostsStatus(Users user, PostsStatus postsStatus);
    Long countByUsers(Users user);
    Optional<Posts> findByIdAndPostsStatus(String postId, PostsStatus postsStatus);
}