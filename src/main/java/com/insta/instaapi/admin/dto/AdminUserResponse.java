package com.insta.instaapi.admin.dto;

import com.insta.instaapi.post.entity.Status;
import com.insta.instaapi.user.entity.UserStatus;
import com.insta.instaapi.user.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AdminUserResponse {

    private String userId;
    private String email;
    private String nickname;
    private String name;
    private String introduction;
    private String sex;
    private UserStatus status;

    @Builder
    public AdminUserResponse(String userId, String email, String nickname, String name, String introduction, String sex, UserStatus status) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.introduction = introduction;
        this.sex = sex;
        this.status = status;
    }

    public static List<AdminUserResponse> of(List<Users> usersList) {
        List<AdminUserResponse> result = new ArrayList<>();

        for (Users user: usersList) {
            AdminUserResponse userList = AdminUserResponse.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .name(user.getName())
                    .introduction(user.getIntroduction())
                    .sex(user.getSex())
                    .status(user.getStatus())
                    .build();
            result.add(userList);
        }

        return result;
    }
}
