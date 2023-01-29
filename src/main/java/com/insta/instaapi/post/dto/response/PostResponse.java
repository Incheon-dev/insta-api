package com.insta.instaapi.post.dto.response;

import com.insta.instaapi.post.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponse {

    private String id;
    private String postsContent;
    private String location;
    private Boolean isComment;
    private List<String> photos;

    @Builder
    public PostResponse(String id, String postsContent, String location, Boolean isComment, List<String> photos) {
        this.id = id;
        this.postsContent = postsContent;
        this.location = location;
        this.isComment = isComment;
        this.photos = photos;
    }

    public static PostResponse toEntity(Posts post, List<String> photos) {
        return PostResponse.builder()
                .id(post.getId())
                .postsContent(post.getPostsContent())
                .location(post.getLocation())
                .isComment(post.getIsComment())
                .photos(photos)
                .build();
    }

    public static PostResponse of(Posts post, List<String> photos) {
       return PostResponse.builder()
                .id(post.getId())
                .postsContent(post.getPostsContent())
                .location(post.getLocation())
                .isComment(post.getIsComment())
                .photos(photos)
                .build();

    }
}
