package com.insta.instaapi.user.dto.response;

import com.insta.instaapi.user.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CurrentUserResponse {

    private String phoneNumber;
    private String email;
    private String name;
    private String nickname;
    private String profileImage;
    private String sex;

    @Builder
    public CurrentUserResponse(String phoneNumber, String email, String name, String nickname, String profileImage, String sex) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.sex = sex;
    }

    public static CurrentUserResponse of(Users user) {
        return CurrentUserResponse.builder()
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .sex(user.getSex())
                .build();
    }
}
