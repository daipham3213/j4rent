package io.tomcode.j4rent.exception;

public class IdCardIsExistsException extends Exception{
    public IdCardIsExistsException() {super("IdCard already exists");
    }
}
