package com.insta.instaapi.user.dto.response;

import com.insta.instaapi.user.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String profileImage;
    private String email;
    private String name;
    private String nickname;
    private String introduction;
    private String sex;
    private Boolean isFollowing;
    private Boolean isFollowed;
    private Long postsCount;
    private Long follower;
    private Long following;

    @Builder
    public UserResponse(String id, String profileImage, String email, String name, String nickname, String introduction, String sex,
                        Boolean isFollowing, Boolean isFollowed, long postsCount, long follower, long following) {
        this.id = id;
        this.profileImage = profileImage;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
        this.sex = sex;
        this.isFollowing = isFollowing;
        this.isFollowed = isFollowed;
        this.postsCount = postsCount;
        this.follower = follower;
        this.following = following;
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

    public static UserResponse search(Users user, Boolean isFollowing, Boolean isFollowed) {
        return UserResponse.builder()
                .id(user.getId())
                .profileImage(user.getProfileImage())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .introduction(user.getIntroduction())
                .sex(user.getSex())
                .isFollowing(isFollowing)
                .isFollowed(isFollowed)
                .build();
    }

    public static UserResponse info(Users user, Boolean isFollowing, Boolean isFollowed, Long countPost, Long countFollower, Long countFollow) {
        return UserResponse.builder()
                .id(user.getId())
                .profileImage(user.getProfileImage())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .introduction(user.getIntroduction())
                .sex(user.getSex())
                .isFollowing(isFollowing)
                .isFollowed(isFollowed)
                .postsCount(countPost)
                .follower(countFollower)
                .following(countFollow)
                .build();
    }
}
