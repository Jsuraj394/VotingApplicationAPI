package com.Employee.Employee.ExceptionHandler;

public class EmpAlreadyExistException extends RuntimeException{
    public EmpAlreadyExistException(String message) {
        super(message);
    }
}
