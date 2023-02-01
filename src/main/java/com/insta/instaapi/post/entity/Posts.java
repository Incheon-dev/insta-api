package com.insta.instaapi.post.entity;

import com.insta.instaapi.post.dto.request.PostRequest;
import com.insta.instaapi.post.dto.request.UpdatePostRequest;
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
    private Status postsStatus;

    @Builder
    public Posts(Users users, String postsContent, String location, Boolean isHide, Boolean isComment, Status postsStatus) {
        this.users = users;
        this.postsContent = postsContent;
        this.location = location;
        this.isHide = isHide;
        this.isComment = isComment;
        this.postsStatus = postsStatus;
    }

    public Posts create(PostRequest request, Users users, Status postsStatus) {
        return Posts.builder()
                .users(users)
                .postsContent(request.getPostContent())
                .location(request.getLocation())
                .isHide(request.getIsHide())
                .isComment(request.getIsComment())
                .postsStatus(postsStatus)
                .build();
    }

    public void update(UpdatePostRequest request) {
        this.postsContent = request.getPostId();
        this.location = request.getLocation();
        this.isHide = request.getIsHide();
        this.isComment = request.getIsComment();
    }


    public void delete(Status postsStatus) {
        this.postsStatus = postsStatus;
    }
}
