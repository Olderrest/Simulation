package com.stasuma.exception;


public class RouteTypeException extends Exception{

    public RouteTypeException(String message) {
        super(message);
    }

    public RouteTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
