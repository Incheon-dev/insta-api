package com.insta.instaapi.admin.service;

import com.insta.instaapi.admin.dto.AdminUserResponse;
import com.insta.instaapi.user.dto.request.SignUpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService {
    String signup(SignUpRequest request);

    List<AdminUserResponse> allUsers(HttpServletRequest httpServletRequest);
}
