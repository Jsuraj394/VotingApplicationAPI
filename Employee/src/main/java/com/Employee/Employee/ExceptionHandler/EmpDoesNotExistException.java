package com.Employee.Employee.ExceptionHandler;

public class EmpDoesNotExistException extends RuntimeException{
    public EmpDoesNotExistException(String message) {
        super(message);
    }
}
