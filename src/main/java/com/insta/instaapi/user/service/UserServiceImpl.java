package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;
import com.insta.instaapi.user.dto.response.UserResponse;
import com.insta.instaapi.user.entity.Authority;
import com.insta.instaapi.user.entity.UserStatus;
import com.insta.instaapi.user.entity.Users;
import com.insta.instaapi.user.entity.UsersBlock;
import com.insta.instaapi.user.entity.repository.UsersBlockRepository;
import com.insta.instaapi.user.entity.repository.UsersRepository;
import com.insta.instaapi.user.entity.repository.queryDSL.DslUsersBlockRepository;
import com.insta.instaapi.user.exception.UserDuplicatedException;
import com.insta.instaapi.user.exception.UserException;
import com.insta.instaapi.user.exception.UserNotFoundException;
import com.insta.instaapi.utils.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final UsersBlockRepository usersBlockRepository;
    private final DslUsersBlockRepository dslUsersBlockRepository;

    @Override
    public String create(SignUpRequest request) {

        if (usersRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new UserDuplicatedException("해당 번호로 가입된 유저가 존재합니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return usersRepository.save(new Users().create(request, authority, passwordEncoder, UserStatus.ACTIVATED)).getName();
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean validate(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public String reset(UpdatePasswordRequest request) {
        Users user = findByEmail(request.getEmail());
        user.reset(passwordEncoder.encode(request.getNewPassword()));

        return user.getId();
    }

    @Override
    public UserResponse search(HttpServletRequest servletRequest, String email) {
        existsByUserIdAndOtherUserId(current(servletRequest), findByEmail(email).getId());

        Users user = findByEmail(email);
        return UserResponse.of(user);
    }

    @Override
    public List<UserResponse> blockList(HttpServletRequest httpServletRequest) {
        List<UsersBlock> blockList = usersBlockRepository.findAllByUsers(current(httpServletRequest));
        List<String> otherUserList = blockList.stream().map(block -> block.getOtherUserId()).collect(Collectors.toList());
        return findByUserId(otherUserList);
    }

    @Override
    public void unblock(HttpServletRequest httpServletRequest, String email) {
        usersBlockRepository.deleteByUsersAndOtherUserId(current(httpServletRequest), findByEmail(email).getId());
    }

    @Override
    public String follow(HttpServletRequest httpServletRequest, String email) {
        isFollowedUser(current(httpServletRequest), findByEmail(email).getId());
        return null;
    }

    @Override
    public String block(HttpServletRequest httpServletRequest, String email) {
        return usersBlockRepository.save(new UsersBlock().create(current(httpServletRequest), findByEmail(email).getId())).getId();
    }

    private List<UserResponse> findByUserId(List<String> otherUserList) {
        List<UserResponse> result = new ArrayList<>();
        for (String userId: otherUserList) {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

            result.add(UserResponse.of(user));
        }
        return result;
    }

    public Users current(HttpServletRequest servletRequest) {
        String info = jwtService.token(servletRequest).getSubject();
        return findByEmail(info);
    }

    private Users findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
    }

    public void existsByEmailAndPhoneNumberAndName(String email, String phoneNumber, String name) {
        if (!usersRepository.existsByEmailAndPhoneNumberAndName(email, phoneNumber, name)) {
            throw new UserNotFoundException("해당 유저를 찾을 수 없습니다.");
        }
    }

    private void existsByUserIdAndOtherUserId(Users users, String otherUserId) {
        if (usersBlockRepository.existsByUsersAndOtherUserId(users, otherUserId)) {
            throw new UserException("차단된 유저입니다.");
        }
    }

    private String isFollowedUser(Users users, String otherUserId) {

    }
}
