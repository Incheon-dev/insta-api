package com.insta.instaapi.user.controller;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/sign-up")
    public ResponseEntity<String> create(@RequestBody SignUpRequest request) {
        String response = "";
        try {
            response = userService.create(request);
        } catch (Exception e) {
            log.error("Can not create user.");
        }
        return ResponseEntity.ok(response);
    }
}
