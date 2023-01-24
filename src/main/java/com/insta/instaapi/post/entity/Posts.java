package com.insta.instaapi.post.entity;

import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Posts extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Column
    private String postsContent;

    @Column
    private String location;

    @Column
    private Boolean isHide;

    @Column
    private Boolean isComment;

    @Enumerated(value = EnumType.STRING)
    @Column
    private PostsStatus postsStatus;

    @Builder
    public Posts(Users users, String postsContent, String location, Boolean isHide, Boolean isComment, PostsStatus postsStatus) {
        this.users = users;
        this.postsContent = postsContent;
        this.location = location;
        this.isHide = isHide;
        this.isComment = isComment;
        this.postsStatus = postsStatus;
    }

    public Posts create(PostRequest request, Users users) {
        return Posts.builder()
                .users(users)
                .postsContent(request.getPostContent())
                .location(request.getLocation())
                .isHide(request.getIsHide())
                .isComment(request.getIsComment())
                .postsStatus(request.getPostsStatus())
                .build();
    }
}
