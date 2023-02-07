package com.insta.instaapi.admin.service;

import com.insta.instaapi.admin.dto.request.AdminSearchRequest;
import com.insta.instaapi.admin.dto.response.AdminUserResponse;
import com.insta.instaapi.admin.dto.response.AdminUserSearchResponse;
import com.insta.instaapi.user.dto.request.SignUpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService {
    String signup(SignUpRequest request);

    List<AdminUserResponse> allUsers(HttpServletRequest httpServletRequest);

    AdminUserSearchResponse search(HttpServletRequest httpServletRequest, AdminSearchRequest request);
}
