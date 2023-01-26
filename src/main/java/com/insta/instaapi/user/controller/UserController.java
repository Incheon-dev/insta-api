package com.insta.instaapi.user.controller;

import com.insta.instaapi.user.dto.request.SignUpRequest;
import com.insta.instaapi.user.dto.request.UpdatePasswordRequest;
import com.insta.instaapi.user.dto.response.UserResponse;
import com.insta.instaapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/account/sign-up")
    public ResponseEntity<String> create(@Valid @RequestBody SignUpRequest request) {
        String response = "";

        try {
            response = userService.create(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = e.getMessage();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/account")
    public ResponseEntity<Boolean> validate(@RequestParam String email) {
        Boolean response;

        try {
            response = userService.validate(email);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = null;
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/account")
    public ResponseEntity<String> reset(@Valid @RequestBody UpdatePasswordRequest request) {
        String response = "";

        try {
            response = userService.reset(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = e.getMessage();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/search")
    public @ResponseBody ResponseEntity<UserResponse> search(HttpServletRequest httpServletRequest, @RequestParam String email) {
        UserResponse response = null;

        try {
            response = userService.search(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
