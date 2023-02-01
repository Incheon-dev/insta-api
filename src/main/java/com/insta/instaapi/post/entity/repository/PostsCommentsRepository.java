package com.insta.instaapi.post.entity.repository;

import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.post.entity.PostsComments;
import com.insta.instaapi.post.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsCommentsRepository extends JpaRepository<PostsComments, String> {

    Long countByPostsAndPostsCommentsStatus(Posts post, Status status);
    List<PostsComments> findByPosts(Posts post);
}
