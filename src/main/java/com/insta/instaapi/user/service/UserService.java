package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;
import com.insta.instaapi.user.dto.response.UserResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    String create(SignUpRequest request);

    Boolean validate(String email);

    String reset(UpdatePasswordRequest request);

    UserResponse search(HttpServletRequest httpServletRequest, String email);

    String block(HttpServletRequest httpServletRequest, String email);

    List<UserResponse> blockList(HttpServletRequest httpServletRequest);
}
