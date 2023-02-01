package com.insta.instaapi.post.entity.repository;

import com.insta.instaapi.post.entity.PostsComments;
import com.insta.instaapi.post.entity.PostsCommentsLike;
import com.insta.instaapi.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsCommentsLikeRepository extends JpaRepository<PostsCommentsLike, String> {

    boolean existsByUsersAndPostsComments(Users user, PostsComments comment);
    void deleteByUsersAndPostsComments(Users user, PostsComments comment);
}

