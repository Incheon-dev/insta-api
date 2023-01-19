package com.insta.instaapi.verification.service;

import com.insta.instaapi.verification.dto.request.VerificationRequest;

public interface VerificationService {
    void sendVerificationNumber(String email);

    Boolean verify(VerificationRequest request);
}
