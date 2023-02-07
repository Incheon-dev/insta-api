package com.insta.instaapi.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String phoneNumber;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    private String nickname;
    private String profileImage;
    @NotBlank
    private String password;
    private String introduction;
    @NotBlank
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
