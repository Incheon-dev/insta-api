package com.insta.instaapi.user.service;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;

public interface UserService {
    String create(SignUpRequest request);

    Boolean validate(String email);

    String reset(UpdatePasswordRequest request);
}
