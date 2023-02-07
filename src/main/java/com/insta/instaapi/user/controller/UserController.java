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
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest request) {
        String response = "";

        try {
            response = userService.signup(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/account")
    public ResponseEntity<?> validateEmail(@RequestParam String email) {
        Boolean response;

        try {
            response = userService.validateEmail(email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/account")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody UpdatePasswordRequest request) {
        String response = "";

        try {
            response = userService.resetPassword(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/search")
    public ResponseEntity<?> search(HttpServletRequest httpServletRequest, @RequestParam String email) {
        UserResponse response = null;

        try {
            response = userService.search(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/user/block")
    public ResponseEntity<?> block(HttpServletRequest httpServletRequest, @RequestParam String email) {
        String response = "";

        try {
            response = userService.block(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/block/list")
    public ResponseEntity<?> blockList(HttpServletRequest httpServletRequest) {
        List<UserResponse> response = null;

        try {
            response = userService.blockList(httpServletRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/user/unblock")
    public ResponseEntity<?> unblock(HttpServletRequest httpServletRequest, @RequestParam String email) {
        try {
            userService.unblock(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/user/follow")
    public ResponseEntity<?> follow(HttpServletRequest httpServletRequest, @RequestParam String email) {
        String response = "";

        try {
            response = userService.follow(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());

        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/user/unfollow")
    public ResponseEntity<?> unfollow(HttpServletRequest httpServletRequest, @RequestParam String email) {
        try {
            userService.unfollow(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> userInfo(HttpServletRequest httpServletRequest, @RequestParam String email) {
        UserResponse response = null;

        try {
            response = userService.userInfo(httpServletRequest, email);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/user/profile-image")
    public ResponseEntity<?> profileImage(HttpServletRequest httpServletRequest, @RequestParam String imageUrl) {
        String response = "";

        try {
            response = userService.profileImage(httpServletRequest, imageUrl);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/api/user/delete/profile-image")
    public ResponseEntity<?> deleteProfileImage(HttpServletRequest httpServletRequest) {
        String response = "";

        try {
            response = userService.deleteProfileImage(httpServletRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
