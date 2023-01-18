package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String create(SignUpRequest request) {
        Users user = new Users();
        return userRepository.save(user.create(request)).getId();
    }
}
