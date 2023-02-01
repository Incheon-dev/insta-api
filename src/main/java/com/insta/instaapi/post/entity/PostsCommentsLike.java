package com.insta.instaapi.post.entity;

import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posts_comments_like")
public class PostsCommentsLike extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "comments_id", nullable = false)
    private PostsComments postsComments;

    public PostsCommentsLike(Users users, PostsComments postsComments) {
        this.users = users;
        this.postsComments = postsComments;
    }
}
