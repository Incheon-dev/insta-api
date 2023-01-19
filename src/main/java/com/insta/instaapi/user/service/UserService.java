package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;

public interface UserService {
    String create(SignUpRequest request);

    Boolean validate(String email);
}
