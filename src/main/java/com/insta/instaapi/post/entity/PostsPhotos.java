package com.insta.instaapi.post.entity;

import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posts_photos")
public class PostsPhotos extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "posts_id", nullable = false)
    private Posts posts;

    @Column
    private String photo;

    @Builder
    public PostsPhotos(Posts posts, String photo) {
        this.posts = posts;
        this.photo = photo;
    }
}
