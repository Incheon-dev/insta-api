package com.insta.instaapi.utils.security.jwt.service;

import com.insta.instaapi.utils.redis.RedisUtil;
import com.insta.instaapi.utils.security.jwt.dto.request.RefreshTokenRequest;
import com.insta.instaapi.utils.security.jwt.dto.request.SignInRequest;
import com.insta.instaapi.utils.security.jwt.dto.response.TokenResponse;
import com.insta.instaapi.utils.security.jwt.config.JwtFilter;
import com.insta.instaapi.utils.security.jwt.config.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisUtil redisUtil;
    private final long refreshTokenValidityInMilliseconds;

    public AuthServiceImpl(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, RedisUtil redisUtil,
                           @Value("${jwt.refresh-token-in-seconds}") long refreshTokenValidityInMilliseconds) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.redisUtil = redisUtil;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }


    @Override
    public ResponseEntity<TokenResponse> authorize(SignInRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken();
        redisUtil.setDataExpire(request.getEmail(), refreshToken, refreshTokenValidityInMilliseconds);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenResponse(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> reissue(RefreshTokenRequest request) {
        if (!redisUtil.getData(request.getEmail()).equals(request.getRefreshToken())) {
            return ResponseEntity.badRequest().body("토큰이 유효하지 않습니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(request.getAccessToken());
        String accessToken = tokenProvider.createToken(authentication);
        redisUtil.setData(request.getEmail(), request.getRefreshToken());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenResponse(accessToken, request.getRefreshToken()), httpHeaders, HttpStatus.OK);
    }
}
