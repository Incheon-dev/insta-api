package com.insta.instaapi.post.entity;

import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posts_photo")
public class PostsPhoto extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "posts_id", nullable = false)
    private Posts posts;

    @Column
    private String photo;
}
