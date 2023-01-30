package com.insta.instaapi.utils.jwt.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final TokenProvider tokenProvider;

    public Claims token(HttpServletRequest request) {
        String token = request.getHeader(JwtFilter.AUTHORIZATION_HEADER).split(" ")[1].trim();
        return tokenProvider.parseJwtToken(token);
    }
}