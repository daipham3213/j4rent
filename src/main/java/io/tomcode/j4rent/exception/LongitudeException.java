package io.tomcode.j4rent.exception;

public class LongitudeException extends Exception {
    public LongitudeException() {
        super("Longitude is not formatted correctly");
    }
}
