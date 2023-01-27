package com.insta.instaapi.utils.security.jwt.exception;

public class JwtInvalidException extends RuntimeException {

    public JwtInvalidException(String message) {
        super(message);
    }
}
