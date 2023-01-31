package com.insta.instaapi.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdatePostRequest {

    private String postId;
    private List<String> photos;
    private String postContent;
    private String location;
    private Boolean isHide;
    private Boolean isComment;
}
