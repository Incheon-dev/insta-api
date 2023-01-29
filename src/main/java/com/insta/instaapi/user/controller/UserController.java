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
import java.util.List;

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
    public ResponseEntity<UserResponse> search(HttpServletRequest httpServletRequest, @RequestParam String email) {
        UserResponse response = null;

        try {
            response = userService.search(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/user/block")
    public ResponseEntity<String> block(HttpServletRequest httpServletRequest, @RequestParam String email) {
        String response = "";

        try {
            response = userService.block(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = e.getMessage();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/block/list")
    public ResponseEntity<List<UserResponse>> blockList(HttpServletRequest httpServletRequest) {
        List<UserResponse> response = null;

        try {
            response = userService.blockList(httpServletRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/user/unblock")
    public ResponseEntity<Void> unblock(HttpServletRequest httpServletRequest, @RequestParam String email) {
        try {
            userService.unblock(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/user/follow")
    public ResponseEntity<String> follow(HttpServletRequest httpServletRequest, @RequestParam String email) {
        String response = "";

        try {
            response = userService.follow(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
            response = e.getMessage();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/user/unfollow")
    public ResponseEntity<Void> unfollow(HttpServletRequest httpServletRequest, @RequestParam String email) {
        try {
            userService.unfollow(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user")
    public ResponseEntity<UserResponse> info(HttpServletRequest httpServletRequest, @RequestParam String userId) {
        UserResponse response = null;

        try {
            response = userService.info(httpServletRequest, userId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
