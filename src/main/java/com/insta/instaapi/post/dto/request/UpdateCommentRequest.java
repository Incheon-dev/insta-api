package com.insta.instaapi.post.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

    private String commentId;
    private String postsCommentsContent;

    @Builder
    public UpdateCommentRequest(String commentId, String postsCommentsContent) {
        this.commentId = commentId;
        this.postsCommentsContent = postsCommentsContent;
    }
}
