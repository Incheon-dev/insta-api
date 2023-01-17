package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.SignUpRequest;

public interface UserService {
    Long signup(SignUpRequest request);
}
