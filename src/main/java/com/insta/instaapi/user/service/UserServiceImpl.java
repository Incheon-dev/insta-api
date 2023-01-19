package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.entity.Authority;
import com.insta.instaapi.user.entity.UserStatus;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UserRepository;
import com.insta.instaapi.user.exception.UserDuplicatedEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public String create(SignUpRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserDuplicatedEmailException("이미 가입한 유저가 존재합니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return userRepository.save(new Users().create(request, authority, passwordEncoder, UserStatus.ACTIVATED)).getId();
    }
}
