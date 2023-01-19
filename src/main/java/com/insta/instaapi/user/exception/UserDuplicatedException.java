package com.insta.instaapi.user.exception;

public class UserDuplicatedException extends RuntimeException {

    public UserDuplicatedException(String message) {
        super(message);
    }
}
