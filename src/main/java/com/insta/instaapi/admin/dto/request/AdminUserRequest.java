package com.insta.instaapi.admin.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminUserRequest {

    private String phoneNumber;
    private String email;
    private String name;
    private String nickname;
    private String profileImage;
    private String password;
    private String introduction;

    @Builder
    public AdminUserRequest(String phoneNumber, String email, String name, String nickname, String profileImage, String password, String introduction) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.password = password;
        this.introduction = introduction;
    }
}
