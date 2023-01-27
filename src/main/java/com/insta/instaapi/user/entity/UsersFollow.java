package com.insta.instaapi.user.entity;

import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users_follow")
public class UsersFollow extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Column
    private String otherUserId;

    @Builder
    public UsersFollow(Users users, String otherUserId) {
        this.users = users;
        this.otherUserId = otherUserId;
    }
}
