package com.insta.instaapi.admin.service;

import com.insta.instaapi.admin.dto.request.AdminSearchRequest;
import com.insta.instaapi.admin.dto.response.AdminUserResponse;
import com.insta.instaapi.admin.dto.response.AdminUserSearchResponse;
import com.insta.instaapi.admin.entity.repository.queryDSL.DslAdminUserRepository;
import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.entity.Authority;
import com.insta.instaapi.user.entity.UserStatus;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UsersRepository;
import com.insta.instaapi.user.exception.UserDuplicatedException;
import com.insta.instaapi.user.service.UserServiceImpl;
import com.insta.instaapi.utils.jwt.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final UsersRepository usersRepository;
    private final DslAdminUserRepository dslAdminUserRepository;

    @Override
    public String signup(SignUpRequest request) {

        if (usersRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new UserDuplicatedException("해당 번호로 가입된 유저가 존재합니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        return usersRepository.save(new Users().create(request, authority, passwordEncoder, UserStatus.ACTIVATED)).getName();
    }

    @Override
    public List<AdminUserResponse> allUsers(HttpServletRequest httpServletRequest) {
        return AdminUserResponse.of(userService.allUsers());
    }

    @Override
    public AdminUserSearchResponse search(HttpServletRequest httpServletRequest, AdminSearchRequest request) {
        System.out.println(dslAdminUserRepository.searchUser(request));
        return dslAdminUserRepository.searchUser(request);
    }

    public Users current(HttpServletRequest httpServletRequest) {
        return userService.current(httpServletRequest);
    }
}
