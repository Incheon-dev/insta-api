package com.insta.instaapi.post.entity.repository.queryDSL;

import com.insta.instaapi.post.dto.response.InfoResponse;
import com.insta.instaapi.post.dto.response.QInfoResponse;
import com.insta.instaapi.post.entity.PostsStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.insta.instaapi.post.entity.QPosts.posts;
import static com.insta.instaapi.user.entity.QUsers.users;
import static com.insta.instaapi.user.entity.QUsersFollow.usersFollow;

@Repository
@RequiredArgsConstructor
public class DslPostsRepository {
    
    private final JPAQueryFactory jpaQueryFactory;

    public List<InfoResponse> followingPosts(String userId) {
        return jpaQueryFactory
                .select(new QInfoResponse(users.id, users.email, users.name, users.nickname, users.profileImage, posts.id,
                        posts.postsContent, posts.location, posts.isComment))
                .from(posts)
                .innerJoin(usersFollow).on(posts.users.id.eq(usersFollow.followed.id))
                .innerJoin(users).on(posts.users.id.eq(users.id))
                .where(usersFollow.following.id.eq(userId)
                        .and(posts.postsStatus.eq(PostsStatus.NOT_DELETED)))
                .fetchJoin()
                .fetch();
    }
}
