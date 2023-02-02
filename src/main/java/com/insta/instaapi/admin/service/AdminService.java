package com.insta.instaapi.admin.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;

public interface AdminService {
    String signup(SignUpRequest request);
}
