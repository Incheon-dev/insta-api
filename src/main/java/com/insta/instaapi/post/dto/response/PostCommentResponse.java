package com.insta.instaapi.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCommentResponse {

    private String commentId;
    private String userId;
    private String email;
    private String name;
    private String profileImage;
    private String postsCommentsContent;

    @QueryProjection
    @Builder
    public PostCommentResponse(String commentId, String userId, String email, String name, String profileImage, String postsCommentsContent) {
        this.commentId = commentId;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
        this.postsCommentsContent = postsCommentsContent;
    }
}
