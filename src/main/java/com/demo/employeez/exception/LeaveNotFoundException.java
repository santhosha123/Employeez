package com.demo.employeez.exception;

public class LeaveNotFoundException extends RuntimeException{
    public LeaveNotFoundException(String message) {
        super(message);
    }

    public LeaveNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeaveNotFoundException(Throwable cause) {
        super(cause);
    }
}
