package com.insta.instaapi.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    private String email;
    private String newPassword;

    @Builder
    public UpdatePasswordRequest(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }
}
