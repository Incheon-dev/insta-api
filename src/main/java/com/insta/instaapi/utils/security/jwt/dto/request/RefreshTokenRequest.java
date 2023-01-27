package com.insta.instaapi.utils.security.jwt.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenRequest {

    private String email;
    private String accessToken;
    private String refreshToken;
}
