package com.insta.instaapi.utils.security.jwt.service;

import com.insta.instaapi.utils.security.jwt.dto.request.RefreshTokenRequest;
import com.insta.instaapi.utils.security.jwt.dto.request.SignInRequest;
import com.insta.instaapi.utils.security.jwt.dto.response.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<TokenResponse> authorize(SignInRequest request);

    ResponseEntity<?> token(RefreshTokenRequest request);
}
