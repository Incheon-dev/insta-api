package com.insta.instaapi.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotNull
    private String phoneNumber;
    @Email
    private String email;
    @NotNull
    private String name;
    private String nickname;
    private String profileImage;
    @NotNull
    private String password;
    private String introduction;
    private String sex;

    @Builder
    public SignUpRequest(String phoneNumber, String email, String name, String nickname, String profileImage, String password,
                         String introduction, String sex) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.password = password;
        this.introduction = introduction;
        this.sex = sex;
    }
}
