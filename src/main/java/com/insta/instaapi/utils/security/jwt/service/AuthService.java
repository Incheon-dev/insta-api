package com.insta.instaapi.utils.security.jwt.service;

import com.insta.instaapi.utils.security.dto.SignInRequest;
import com.insta.instaapi.utils.security.dto.TokenDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<TokenDto> authorize(SignInRequest request);
}
