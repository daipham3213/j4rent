package io.tomcode.j4rent.exception;

public class IdUserIsNotFoundException extends Exception {
    public IdUserIsNotFoundException() {
        super("User id not found");
    }
}
