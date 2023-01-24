package com.insta.instaapi.post.entity.repository;

import com.insta.instaapi.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, String>  {
}