package com.insta.instaapi.utils.jwt.service;

import com.insta.instaapi.utils.jwt.config.TokenProvider;
import com.insta.instaapi.utils.redis.RedisUtil;
import com.insta.instaapi.utils.jwt.dto.request.RefreshTokenRequest;
import com.insta.instaapi.utils.jwt.dto.request.SignInRequest;
import com.insta.instaapi.utils.jwt.dto.response.TokenResponse;
import com.insta.instaapi.utils.jwt.config.JwtFilter;
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
    private final long adminRefreshTokenValidityInMilliseconds;

    public AuthServiceImpl(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, RedisUtil redisUtil,
                           @Value("${jwt.refresh-token-in-seconds}") long refreshTokenValidityInMilliseconds,
                           @Value("${jwt.admin-refresh-token-in-seconds}") long adminRefreshTokenValidityInMilliseconds) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.redisUtil = redisUtil;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.adminRefreshTokenValidityInMilliseconds = adminRefreshTokenValidityInMilliseconds;
    }


    @Override
    public ResponseEntity<TokenResponse> login(SignInRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);
        redisUtil.setDataExpire(request.getEmail(), refreshToken, refreshTokenValidityInMilliseconds);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenResponse(accessToken), httpHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> reissue(RefreshTokenRequest request) {
        String refreshToken = redisUtil.getData(request.getEmail());

        if (refreshToken == null) {
            return ResponseEntity.badRequest().body("토큰이 유효하지 않습니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        String accessToken = tokenProvider.createToken(authentication);
        redisUtil.setData(request.getEmail(), refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenResponse(accessToken), httpHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> adminLogin(SignInRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createAdminToken(authentication);
        String refreshToken = tokenProvider.createAdminRefreshToken(authentication);
        redisUtil.setDataExpire(request.getEmail(), refreshToken, adminRefreshTokenValidityInMilliseconds);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenResponse(accessToken), httpHeaders, HttpStatus.OK);
    }
}
