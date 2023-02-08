package com.insta.instaapi.admin.entity;

import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.post.entity.PostsComments;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "reports")
public class Reports extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users reportUserId;

    @ManyToOne
    @JoinColumn(name = "other_user_id", nullable = false)
    private Users beReportedUserId;

    @ManyToOne
    @JoinColumn(name = "posts_id", nullable = false)
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "comments_id", nullable = false)
    private PostsComments postsComments;

    @Column
    private String reason;

    @Column
    private Long reportNumber;
}
