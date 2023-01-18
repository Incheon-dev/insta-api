package com.insta.instaapi.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    private String phoneNumber;
    private String email;
    private String username;
    private String name;
    private String nickname;
    private String password;
    private String introduction;
    private String sex;

    @Builder
    public SignUpRequest(String phoneNumber, String email, String username, String name, String nickname, String password, String introduction,
                         String sex) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.introduction = introduction;
        this.sex = sex;
    }
}
