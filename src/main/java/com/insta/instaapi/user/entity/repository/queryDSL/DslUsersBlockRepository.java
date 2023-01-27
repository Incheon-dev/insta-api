package com.insta.instaapi.user.entity.repository.queryDSL;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DslUsersBlockRepository {

    private JPAQueryFactory jpaQueryFactory;

}
