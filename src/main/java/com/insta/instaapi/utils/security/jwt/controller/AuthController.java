package com.insta.instaapi.utils.security.jwt.controller;

import com.insta.instaapi.utils.security.dto.SignInRequest;
import com.insta.instaapi.utils.security.dto.TokenDto;
import com.insta.instaapi.utils.security.jwt.JwtFilter;
import com.insta.instaapi.utils.security.jwt.TokenProvider;
import com.insta.instaapi.utils.security.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/account/authenticate")
    public ResponseEntity<TokenDto> authorize(@RequestBody SignInRequest request) {
        return authService.authorize(request);
    }
}
