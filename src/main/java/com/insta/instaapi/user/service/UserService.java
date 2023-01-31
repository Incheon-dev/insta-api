package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;
import com.insta.instaapi.user.dto.response.UserResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    String signup(SignUpRequest request);

    Boolean validateEmail(String email);

    String resetPassword(UpdatePasswordRequest request);

    UserResponse search(HttpServletRequest httpServletRequest, String email);

    String block(HttpServletRequest httpServletRequest, String email);

    List<UserResponse> blockList(HttpServletRequest httpServletRequest);

    void unblock(HttpServletRequest httpServletRequest, String email);

    String follow(HttpServletRequest httpServletRequest, String email);

    void unfollow(HttpServletRequest httpServletRequest, String email);

    UserResponse userInfo(HttpServletRequest httpServletRequest, String email);

    String profileImage(HttpServletRequest httpServletRequest, String imageUrl);

    String deleteProfileImage(HttpServletRequest httpServletRequest);
}
