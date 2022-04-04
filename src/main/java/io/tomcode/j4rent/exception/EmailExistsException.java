package io.tomcode.j4rent.exception;

public class EmailExistsException extends Exception {
    public EmailExistsException() {
        super("Email is already exist!");
    }

}
