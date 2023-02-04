package com.insta.instaapi.utils.jwt.controller;

import com.insta.instaapi.utils.jwt.dto.request.RefreshTokenRequest;
import com.insta.instaapi.utils.jwt.dto.request.SignInRequest;
import com.insta.instaapi.utils.jwt.dto.response.TokenResponse;
import com.insta.instaapi.utils.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/account/login")
    public ResponseEntity<TokenResponse> login(@RequestBody SignInRequest request) {
        return authService.login(request);
    }

    @PostMapping("/api/account/reissue")
    public ResponseEntity<?> reissue(@RequestBody RefreshTokenRequest request) {
        return authService.reissue(request);
    }

    @PostMapping("/api/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody SignInRequest request) {
        return authService.adminLogin(request);
    }
}
