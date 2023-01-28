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

    @ManyToOne
    @JoinColumn(name = "other_user_id", nullable = false)
    private Users blockedUser;

    @Builder
    public UsersBlock(Users users, Users blockedUser) {
        this.users = users;
        this.blockedUser = blockedUser;
    }

    public UsersBlock create(Users users, Users blockedUser) {
        return UsersBlock.builder()
                .users(users)
                .blockedUser(blockedUser)
                .build();
    }
}
