package com.insta.instaapi.admin.entity.repository.queryDSL;

import com.insta.instaapi.admin.dto.request.AdminSearchRequest;
import com.insta.instaapi.admin.dto.response.AdminUserSearchResponse;
import com.insta.instaapi.admin.dto.response.QAdminUserSearchResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.insta.instaapi.user.entity.QUsers.users;

@Repository
@RequiredArgsConstructor
public class DslAdminUserRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public AdminUserSearchResponse searchUser(AdminSearchRequest request) {
        return jpaQueryFactory
                .select(new QAdminUserSearchResponse(users.id, users.createdDate, users.email, users.nickname, users.phoneNumber, users.name, users.status))
                .from(users)
                .where(eqEmail(request.getEmail())
                        .or(eqPhoneNumber(request.getPhoneNumber()))
                        .or(eqSex(request.getSex())))
                .fetchOne();
    }

    private BooleanExpression eqEmail(String email) {
        return users.email.eq(email);
    }

    private BooleanExpression eqPhoneNumber(String phoneNumber) {
        return users.phoneNumber.eq(phoneNumber);
    }

    private BooleanExpression eqSex(String sex) {
        return users.sex.eq(sex);
    }
}
