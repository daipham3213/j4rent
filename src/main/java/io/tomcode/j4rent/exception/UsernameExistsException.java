package io.tomcode.j4rent.exception;

public class UsernameExistsException extends Exception {
    public UsernameExistsException() {
        super("Username is already used");
    }
}
