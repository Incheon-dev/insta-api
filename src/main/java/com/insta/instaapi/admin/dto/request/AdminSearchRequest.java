package com.insta.instaapi.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdminSearchRequest {

    private String email;
    private String phoneNumber;
    private String sex;

    @Builder
    public AdminSearchRequest(String email, String phoneNumber, String sex) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }
}
