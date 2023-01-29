package com.insta.instaapi.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class InfoResponse {

    private String userId;
    private String email;
    private String name;
    private String nickname;
    private String profileImage;
    private String postId;
    private String postsContent;
    private String location;
    private Boolean isComment;
    private List<String> photos;

    @QueryProjection
    public InfoResponse(String userId, String email, String name, String nickname, String profileImage, String postId, String postsContent, String location, Boolean isComment) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.postId = postId;
        this.postsContent = postsContent;
        this.location = location;
        this.isComment = isComment;
    }

    @Builder
    public InfoResponse(String userId, String email, String name, String nickname, String profileImage, String postId, String postsContent, String location, Boolean isComment, List<String> photos) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.postId = postId;
        this.postsContent = postsContent;
        this.location = location;
        this.isComment = isComment;
        this.photos = photos;
    }

    public static InfoResponse of(InfoResponse info, List<String> photos) {
        return InfoResponse.builder()
                .userId(info.getUserId())
                .email(info.getEmail())
                .name(info.getName())
                .nickname(info.getNickname())
                .profileImage(info.getProfileImage())
                .postId(info.getPostId())
                .postsContent(info.getPostsContent())
                .location(info.getLocation())
                .isComment(info.getIsComment())
                .photos(photos)
                .build();

    }
}
