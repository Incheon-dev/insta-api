package com.insta.instaapi.utils.jwt.controller;

import com.insta.instaapi.utils.jwt.dto.request.RefreshTokenRequest;
import com.insta.instaapi.utils.jwt.dto.request.SignInRequest;
import com.insta.instaapi.utils.jwt.dto.response.TokenResponse;
import com.insta.instaapi.utils.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(@RequestBody SignInRequest request) {
        ResponseEntity<?> response = null;

        try {
            response = authService.login(request);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return response;
    }

    @PostMapping("/api/account/reissue")
    public ResponseEntity<?> reissue(@RequestBody RefreshTokenRequest request) {
        ResponseEntity<?> response = null;

        try {
            response = authService.reissue(request);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return response;
    }

    @PostMapping("/api/account/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody SignInRequest request) {
        ResponseEntity<?> response = null;

        try {
            response = authService.adminLogin(request);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return response;
    }
}
