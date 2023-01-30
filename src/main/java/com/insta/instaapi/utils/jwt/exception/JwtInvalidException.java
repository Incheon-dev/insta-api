package com.insta.instaapi.utils.jwt.exception;

public class JwtInvalidException extends RuntimeException {

    public JwtInvalidException(String message) {
        super(message);
    }
}
