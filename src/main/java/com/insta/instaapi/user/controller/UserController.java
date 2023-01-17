package com.insta.instaapi.user.controller;

import com.insta.instaapi.user.dto.SignUpRequest;
import com.insta.instaapi.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/api/v1/sign-up")
    public ResponseEntity<Long> signup(@RequestBody SignUpRequest request) {
        Long response = userService.signup(request);
        return ResponseEntity.ok(response);
    }
}
