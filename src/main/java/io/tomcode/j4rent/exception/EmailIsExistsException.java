package io.tomcode.j4rent.exception;

public class EmailIsExistsException extends Exception {
    public EmailIsExistsException() {
        super("Email is already exist!");
    }

}
