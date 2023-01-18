package com.insta.instaapi.user.entity;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.exception.UserException;
import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "insta_users")
public class Users extends BaseEntity {

    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String nickname;
    @Column
    private String password;
    @Column
    private String introduction;
    @Column
    private String sex;

    @Builder
    public Users(String phoneNumber, String email, String name, String nickname, String password, String introduction, String sex) {
        validate(phoneNumber, email, name, password);
        this.nickname = nickname;
        this.introduction = introduction;
        this.sex = sex;
    }

    private void validate(String phoneNumber, String email, String name, String password) {
        if (Objects.equals(phoneNumber, "") || Objects.equals(email, "") || Objects.equals(name, "") || Objects.equals(password, "")) {
            throw new UserException("공백이 존재합니다.");
        } else {
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.name = name;
            this.password = password;
        }
    }

    public Users create(SignUpRequest request) {
        return Users.builder()
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .name(request.getName())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .introduction(request.getIntroduction())
                .sex(request.getSex())
                .build();
    }

    @Override
    public String toString() {
        return "Users{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", introduction='" + introduction + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
