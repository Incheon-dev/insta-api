package com.insta.instaapi.admin.controller;

import com.insta.instaapi.admin.dto.AdminUserResponse;
import com.insta.instaapi.admin.service.AdminService;
import com.insta.instaapi.user.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/api/admin/sign-up")
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
}
