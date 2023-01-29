package com.insta.instaapi.post.entity.repository;

import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.post.entity.PostsPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsPhotosRepository extends JpaRepository<PostsPhotos, String> {

    List<PostsPhotos> findAllByPosts(Posts posts);
}
