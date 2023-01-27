package com.insta.instaapi.utils.security.jwt.controller;

import com.insta.instaapi.utils.security.jwt.dto.request.RefreshTokenRequest;
import com.insta.instaapi.utils.security.jwt.dto.request.SignInRequest;
import com.insta.instaapi.utils.security.jwt.dto.response.TokenResponse;
import com.insta.instaapi.utils.security.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/account/authenticate")
    public ResponseEntity<TokenResponse> authorize(@RequestBody SignInRequest request) {
        return authService.authorize(request);
    }

    @PostMapping("/api/account/token")
    public ResponseEntity<?> token(@RequestBody RefreshTokenRequest request) {
        return authService.token(request);
    }
}
