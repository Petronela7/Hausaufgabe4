package com.company.exception;

public class NonExistentObjectException extends Exception{
    public NonExistentObjectException(String message)
    {
        super(message);
    }
}
