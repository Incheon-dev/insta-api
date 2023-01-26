package com.insta.instaapi.user.entity;

import com.insta.instaapi.utils.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users_block")
public class UsersBlock extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Column
    private String otherUserId;

    @Builder
    public UsersBlock(Users users, String otherUserId) {
        this.users = users;
        this.otherUserId = otherUserId;
    }

    public UsersBlock create(Users users, String otherUserId) {
        return UsersBlock.builder()
                .users(users)
                .otherUserId(otherUserId)
                .build();
    }
}
