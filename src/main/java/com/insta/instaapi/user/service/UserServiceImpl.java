package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;
import com.insta.instaapi.user.entity.Authority;
import com.insta.instaapi.user.entity.UserStatus;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.repository.UserRepository;
import com.insta.instaapi.user.exception.UserDuplicatedException;
import com.insta.instaapi.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public String create(SignUpRequest request) {

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new UserDuplicatedException("해당 번호로 가입된 유저가 존재합니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return userRepository.save(new Users().create(request, authority, passwordEncoder, UserStatus.ACTIVATED)).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean validate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public String reset(UpdatePasswordRequest request) {
        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        user.reset(passwordEncoder.encode(request.getNewPassword()));

        return user.getId();
    }

    public void findByEmailAndPhoneNumberAndName(String email, String phoneNumber, String name) {
        if (!userRepository.existsByEmailAndPhoneNumberAndName(email, phoneNumber, name)) {
            throw new UserNotFoundException("해당 유저를 찾을 수 없습니다.");
        }
    }
}
