package com.insta.instaapi.verification.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerificationRequest {

    private String email;
    private String authKey;

    @Builder
    public VerificationRequest(String email, String authKey) {
        this.email = email;
        this.authKey = authKey;
    }
}
