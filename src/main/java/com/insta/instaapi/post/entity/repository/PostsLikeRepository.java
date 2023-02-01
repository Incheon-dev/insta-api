package com.insta.instaapi.post.entity.repository;

import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.post.entity.PostsLike;
import com.insta.instaapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsLikeRepository extends JpaRepository<PostsLike, String> {

    void deleteByUsersAndPosts(Users user, Posts post);
    boolean existsByUsersAndPosts(Users user, Posts post);
    Long countByPosts(Posts post);
}
