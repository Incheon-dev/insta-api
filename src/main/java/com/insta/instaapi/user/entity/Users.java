package com.insta.instaapi.user.entity;

import com.insta.instaapi.post.entity.Posts;
import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private String profileImage;

    @Column
    private String password;

    @Column
    private String introduction;

    @Column
    private String sex;

    @Enumerated(value = EnumType.STRING)
    @Column
    private UserStatus status;

    @OneToMany(mappedBy = "users")
    private List<Posts> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public Users(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public Users create(SignUpRequest request, Authority authority, PasswordEncoder passwordEncoder, UserStatus status) {
        return Users.builder()
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .name(request.getName())
                .nickname(request.getNickname())
                .profileImage(request.getProfileImage())
                .password(passwordEncoder.encode(request.getPassword()))
                .introduction(request.getIntroduction())
                .sex(request.getSex())
                .authorities(Collections.singleton(authority))
                .status(status)
                .build();
    }

    public void reset(String newPassword) {
        this.password = newPassword;
    }
}
