package com.insta.instaapi.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    private String username;
    private String password;

    @Builder
    public SignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
