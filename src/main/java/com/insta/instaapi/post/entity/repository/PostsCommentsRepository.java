package com.insta.instaapi.post.entity.repository;

import com.insta.instaapi.post.entity.PostsComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsCommentsRepository extends JpaRepository<PostsComments, String> {
}
