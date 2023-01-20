package com.insta.instaapi.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindRequest {

    private String email;
    private String phoneNumber;
    private String name;

    @Builder
    public FindRequest(String email, String phoneNumber, String name) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
}
