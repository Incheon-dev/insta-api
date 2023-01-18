package com.insta.instaapi.utils.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {

    private String email;
    private String password;
}
