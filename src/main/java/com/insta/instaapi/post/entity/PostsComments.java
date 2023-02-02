package com.insta.instaapi.post.entity;

import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posts_comments")
public class PostsComments extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "posts_id", nullable = false)
    private Posts posts;

    @Column
    private String postsCommentsContent;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Status postsCommentsStatus;

    public PostsComments(Users users, Posts posts, String postsCommentsContent, Status postsCommentsStatus) {
        this.users = users;
        this.posts = posts;
        this.postsCommentsContent = postsCommentsContent;
        this.postsCommentsStatus = postsCommentsStatus;
    }

    public void delete(Status status) {
        this.postsCommentsStatus = status;
    }

    public void update(String postsCommentsContent) {
        this.postsCommentsContent = postsCommentsContent;
    }
}
