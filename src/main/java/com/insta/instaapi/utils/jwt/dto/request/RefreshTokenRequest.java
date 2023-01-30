package com.insta.instaapi.utils.jwt.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenRequest {

    private String email;
    private String accessToken;
}
