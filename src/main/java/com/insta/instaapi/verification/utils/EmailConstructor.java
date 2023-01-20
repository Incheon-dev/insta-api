package com.insta.instaapi.verification.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailConstructor {

    String authKey;
    String subject;
    String text;

    @Builder
    public EmailConstructor(String authKey, String subject, String text) {
        this.authKey = authKey;
        this.subject = subject;
        this.text = text;
    }
}
