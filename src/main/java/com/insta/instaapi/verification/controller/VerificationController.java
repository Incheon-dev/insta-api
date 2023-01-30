package com.insta.instaapi.verification.controller;

import com.insta.instaapi.user.dto.request.FindRequest;
import com.insta.instaapi.verification.dto.request.VerificationRequest;
import com.insta.instaapi.verification.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequiredArgsConstructor
@Log4j2
public class VerificationController {

    private final VerificationService emailService;

    @PostMapping("/api/account/verify")
    public ResponseEntity<Boolean> verifyNumber(@RequestBody VerificationRequest request) {
        Boolean response = null;
        try {
            response = emailService.verifyNumber(request);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/account/verify")
    public ResponseEntity<Void> sendVerificationNumber(@RequestParam String email) {
        try {
            emailService.sendVerificationNumber(email);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/account/find")
    public ResponseEntity<Void> forgetPassword(@RequestBody FindRequest request) {
        try {
            emailService.forgetPassword(request);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
