package io.tomcode.j4rent.exception;

public class InvalidOTPException extends Exception {
    public InvalidOTPException() {
        super("Invalid OTP");
    }
}
