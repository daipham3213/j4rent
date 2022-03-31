package io.tomcode.j4rent.exception;

public class PhoneNumberExistsException extends Exception {
    public PhoneNumberExistsException() {
        super("Phone number is already used");
    }
}
