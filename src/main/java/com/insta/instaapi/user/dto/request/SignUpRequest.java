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

    @NotBlank(message = "공백 또는 빈 값이 존재합니다.")
    private String phoneNumber;
    @Email(message = "이메일 형식이어야 합니다.")
    @NotBlank(message = "공백 또는 빈 값이 존재합니다.")
    private String email;
    @NotBlank(message = "공백 또는 빈 값이 존재합니다.")
    private String name;
    private String nickname;
    private String profileImage;
    @NotBlank(message = "공백 또는 빈 값이 존재합니다.")
    private String password;
    private String introduction;
    @NotBlank(message = "공백 또는 빈 값이 존재합니다.")
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
