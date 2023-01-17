package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.SignUpRequest;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long signup(SignUpRequest request) {
        return userRepository.save(Users.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build())
                .getId();
    }
}
