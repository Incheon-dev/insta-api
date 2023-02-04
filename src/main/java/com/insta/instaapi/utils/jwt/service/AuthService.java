package com.insta.instaapi.utils.jwt.service;

import com.insta.instaapi.utils.jwt.dto.request.RefreshTokenRequest;
import com.insta.instaapi.utils.jwt.dto.request.SignInRequest;
import com.insta.instaapi.utils.jwt.dto.response.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<TokenResponse> login(SignInRequest request);

    ResponseEntity<?> reissue(RefreshTokenRequest request);

    ResponseEntity<?> adminLogin(SignInRequest request);
}
