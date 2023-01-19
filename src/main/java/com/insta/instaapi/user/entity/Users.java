package com.insta.instaapi.user.entity;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
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
    @Column
    private UserStatus status;

    @ManyToMany
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public Users create(SignUpRequest request, Authority authority, PasswordEncoder passwordEncoder, UserStatus status) {
        return Users.builder()
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .name(request.getName())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .introduction(request.getIntroduction())
                .sex(request.getSex())
                .authorities(Collections.singleton(authority))
                .status(status)
                .build();
    }
}
