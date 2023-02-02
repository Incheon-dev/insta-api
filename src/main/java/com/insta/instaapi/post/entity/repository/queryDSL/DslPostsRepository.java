package com.insta.instaapi.post.entity.repository.queryDSL;

import com.insta.instaapi.post.dto.response.InfoResponse;
import com.insta.instaapi.post.dto.response.PostCommentResponse;
import com.insta.instaapi.post.dto.response.QInfoResponse;
import com.insta.instaapi.post.dto.response.QPostCommentResponse;
import com.insta.instaapi.post.entity.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.insta.instaapi.post.entity.QPosts.posts;
import static com.insta.instaapi.post.entity.QPostsComments.postsComments;
import static com.insta.instaapi.user.entity.QUsers.users;
import static com.insta.instaapi.user.entity.QUsersFollow.usersFollow;

@Repository
@RequiredArgsConstructor
public class DslPostsRepository {
    
    private final JPAQueryFactory jpaQueryFactory;

    public List<InfoResponse> followingPosts(String userId, Pageable pageable) {
        return jpaQueryFactory
                .select(new QInfoResponse(users.id, users.email, users.name, users.nickname, users.profileImage, posts.id,
                        posts.postsContent, posts.location, posts.isComment))
                .from(posts)
                .innerJoin(usersFollow).on(posts.users.id.eq(usersFollow.followed.id))
                .innerJoin(users).on(posts.users.id.eq(users.id))
                .where(usersFollow.following.id.eq(userId)
                        .and(posts.postsStatus.eq(Status.NOT_DELETED)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchJoin()
                .fetch();
    }

    public List<PostCommentResponse> postComments(String postId, Pageable pageable) {
        return jpaQueryFactory
                .select(new QPostCommentResponse(postsComments.id, users.id, users.email, users.name, users.profileImage, postsComments.postsCommentsContent))
                .from(postsComments)
                .innerJoin(posts).on(postsComments.posts.id.eq(posts.id))
                .innerJoin(users).on(postsComments.users.id.eq(users.id))
                .where(posts.postsStatus.eq(Status.NOT_DELETED)
                        .and(postsComments.postsCommentsStatus.eq(Status.NOT_DELETED))
                        .and(posts.id.eq(postId)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchJoin()
                .fetch();
    }
}
