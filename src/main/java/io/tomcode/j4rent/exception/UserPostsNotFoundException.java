package io.tomcode.j4rent.exception;

public class UserPostsNotFoundException extends Exception {
    public UserPostsNotFoundException() {
        super("User posts not found");
    }
}
