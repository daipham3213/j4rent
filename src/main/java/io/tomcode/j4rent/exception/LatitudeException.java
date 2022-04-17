package io.tomcode.j4rent.exception;

public class LatitudeException extends Exception {
    public LatitudeException() {
        super("Latitude is not formatted correctly");
    }
}
