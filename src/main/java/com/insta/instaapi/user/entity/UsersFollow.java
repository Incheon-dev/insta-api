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
    private Users following;

    @ManyToOne
    @JoinColumn(name = "other_user_id", nullable = false)
    private Users followed;

    @Builder
    public UsersFollow(Users following, Users followed) {
        this.following = following;
        this.followed = followed;
    }
}
