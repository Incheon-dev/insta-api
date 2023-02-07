package com.insta.instaapi.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdminSearchRequest {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private String email;
    private String phoneNumber;
    private String sex;

    @Builder
    public AdminSearchRequest(LocalDateTime startDate, LocalDateTime endDate, String email, String phoneNumber, String sex) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }
}
