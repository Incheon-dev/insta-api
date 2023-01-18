package com.insta.instaapi.user.exception;

public class UserDuplicatedEmailException extends RuntimeException {

    public UserDuplicatedEmailException(String message) {
        super(message);
    }
}
