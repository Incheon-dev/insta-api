package com.insta.instaapi.user.entity.repository;

import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.UsersFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersFollowRepository extends JpaRepository<UsersFollow, String> {
    boolean existsByFollowingAndFollowed(Users following, Users followed);
    void deleteByFollowingAndFollowed(Users following, Users followed);
    Long countByFollowed(Users followed);
    Long countByFollowing(Users following);
}
