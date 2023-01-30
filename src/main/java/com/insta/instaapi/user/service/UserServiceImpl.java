package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;
import com.insta.instaapi.user.dto.response.UserResponse;
import com.insta.instaapi.user.entity.*;
import com.insta.instaapi.user.entity.repository.UsersBlockRepository;
import com.insta.instaapi.user.entity.repository.UsersFollowRepository;
import com.insta.instaapi.user.entity.repository.UsersRepository;
import com.insta.instaapi.user.exception.UserDuplicatedException;
import com.insta.instaapi.user.exception.UserException;
import com.insta.instaapi.user.exception.UserNotFoundException;
import com.insta.instaapi.utils.jwt.config.JwtService;
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
    private final UsersFollowRepository usersFollowRepository;

    @Override
    public String signup(SignUpRequest request) {

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
    public Boolean validateEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public String resetPassword(UpdatePasswordRequest request) {
        Users user = findByEmail(request.getEmail());
        user.reset(passwordEncoder.encode(request.getNewPassword()));

        return user.getId();
    }

    @Override
    public UserResponse search(HttpServletRequest servletRequest, String email) {
        isBlock(current(servletRequest), findByEmail(email));

        Users user = findByEmail(email);
        return UserResponse.of(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> blockList(HttpServletRequest httpServletRequest) {
        List<UsersBlock> blockList = usersBlockRepository.findAllByUsers(current(httpServletRequest));
        List<String> otherUserList = blockList.stream().map(block -> block.getBlockedUser().getId()).collect(Collectors.toList());
        return findBlockList(otherUserList);
    }

    @Override
    public void unblock(HttpServletRequest httpServletRequest, String email) {
        usersBlockRepository.deleteByUsersAndBlockedUser(current(httpServletRequest), findByEmail(email));
    }

    @Override
    public String follow(HttpServletRequest httpServletRequest, String email) {
        return usersFollowRepository.save(new UsersFollow(current(httpServletRequest), findByEmail(email))).getId();
    }

    @Override
    public void unfollow(HttpServletRequest httpServletRequest, String email) {
        usersFollowRepository.deleteByFollowingAndFollowed(current(httpServletRequest), findByEmail(email));
    }

    @Override
    public UserResponse userInfo(HttpServletRequest httpServletRequest, String userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        return UserResponse.info(user, isFollowing(current(httpServletRequest), user), isFollowed(user, current(httpServletRequest)));
    }

    @Override
    public String block(HttpServletRequest httpServletRequest, String email) {
        return usersBlockRepository.save(new UsersBlock().create(current(httpServletRequest), findByEmail(email))).getId();
    }

    private List<UserResponse> findBlockList(List<String> otherUserList) {
        List<UserResponse> result = new ArrayList<>();
        for (String userId: otherUserList) {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

            result.add(UserResponse.of(user));
        }
        return result;
    }

    public Users current(HttpServletRequest httpServletRequest) {
        String info = jwtService.token(httpServletRequest).getSubject();
        return findByEmail(info);
    }

    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
    }

    public void existsByEmailAndPhoneNumberAndName(String email, String phoneNumber, String name) {
        if (!usersRepository.existsByEmailAndPhoneNumberAndName(email, phoneNumber, name)) {
            throw new UserNotFoundException("해당 유저를 찾을 수 없습니다.");
        }
    }

    private void isBlock(Users users, Users blockedUser) {
        if (usersBlockRepository.existsByUsersAndBlockedUser(blockedUser, users)) {
            throw new UserException("차단된 유저입니다.");
        }
    }

    private Boolean isFollowing(Users following, Users followed) {
        return usersFollowRepository.existsByFollowingAndFollowed(following, followed);
    }

    private Boolean isFollowed(Users followed, Users following) {
        return usersFollowRepository.existsByFollowingAndFollowed(followed, following);
    }

    public Users findById(String userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
    }
}
