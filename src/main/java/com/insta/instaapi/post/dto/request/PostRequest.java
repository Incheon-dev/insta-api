package com.insta.instaapi.post.dto.request;

import com.insta.instaapi.post.entity.PostsStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostRequest {

    private List<String> photos;
    private String postContent;
    private String location;
    private Boolean isHide;
    private Boolean isComment;
    private PostsStatus postsStatus;

    @Builder
    public PostRequest(List<String> photos, String postContent, String location, Boolean isHide, Boolean isComment, PostsStatus postsStatus) {
        this.photos = photos;
        this.postContent = postContent;
        this.location = location;
        this.isHide = isHide;
        this.isComment = isComment;
        this.postsStatus = postsStatus;
    }
}
