package com.insta.instaapi.admin.controller;

import com.insta.instaapi.admin.dto.request.AdminSearchRequest;
import com.insta.instaapi.admin.dto.request.AdminUserRequest;
import com.insta.instaapi.admin.dto.response.AdminUserResponse;
import com.insta.instaapi.admin.dto.response.AdminUserSearchResponse;
import com.insta.instaapi.admin.service.AdminService;
import com.insta.instaapi.user.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/api/account/admin/sign-up")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        String response = "";

        try {
            response = adminService.signup(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/admin/users")
    public ResponseEntity<?> allUsers(HttpServletRequest httpServletRequest) {
        List<AdminUserResponse> response = null;

        try {
            response = adminService.allUsers(httpServletRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/admin/users/search")
    public ResponseEntity<?> search(HttpServletRequest httpServletRequest, @RequestBody AdminSearchRequest request) {
        List<AdminUserSearchResponse> response = null;

        try {
            response = adminService.search(httpServletRequest, request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/admin/users/{userId}")
    public ResponseEntity<?> userInfo(HttpServletRequest httpServletRequest, @PathVariable String userId) {
        AdminUserResponse response = null;

        try {
            response = adminService.userInfo(httpServletRequest, userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/admin/users/{userId}")
    public ResponseEntity<?> block(HttpServletRequest httpServletRequest, @PathVariable String userId) {
        String response = "";

        try {
            response = adminService.block(httpServletRequest, userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/admin/users/{userId}")
    public ResponseEntity<?> modify(HttpServletRequest httpServletRequest, @PathVariable String userId, @RequestBody AdminUserRequest request) {
        String response = null;

        try {
            response = adminService.modify(httpServletRequest, userId, request);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
