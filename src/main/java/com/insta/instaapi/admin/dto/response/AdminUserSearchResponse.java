package com.insta.instaapi.admin.dto.response;

import com.insta.instaapi.user.entity.UserStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdminUserSearchResponse {

    private String userId;
    private LocalDateTime createdDate;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String name;
    private UserStatus status;

    @QueryProjection
    @Builder
    public AdminUserSearchResponse(String userId, LocalDateTime createdDate, String email, String nickname, String phoneNumber, String name, UserStatus status) {
        this.userId = userId;
        this.createdDate = createdDate;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.status = status;
    }
}
