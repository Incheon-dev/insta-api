package com.insta.instaapi.utils.security.jwt.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    private String accessToken;
    private String refreshToken;
}
