package com.insta.instaapi.user.dto.response;

import com.insta.instaapi.user.entity.Users;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String email;
    private String name;
    private String nickname;
    private String introduction;
    private String sex;
    private Boolean isFollowing;
    private Boolean isFollowed;

    @Builder
    public UserResponse(String id, String email, String name, String nickname, String introduction, String sex, Boolean isFollowing, Boolean isFollowed) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.sex = sex;
        this.isFollowing = isFollowing;
        this.isFollowed = isFollowed;
    }

    public static UserResponse of(Users user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .introduction(user.getIntroduction())
                .sex(user.getSex())
                .build();
    }

    public static UserResponse info(Users user, Boolean isFollowing, Boolean isFollowed) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .introduction(user.getIntroduction())
                .sex(user.getSex())
                .isFollowing(isFollowing)
                .isFollowed(isFollowed)
                .build();
    }
}
