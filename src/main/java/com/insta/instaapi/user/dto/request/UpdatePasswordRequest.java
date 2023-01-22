package com.insta.instaapi.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @Email
    private String email;
    @NotNull
    private String newPassword;

    @Builder
    public UpdatePasswordRequest(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }
}
