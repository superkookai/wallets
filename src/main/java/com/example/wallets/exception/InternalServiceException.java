package com.example.wallets.exception;

public class InternalServiceException extends RuntimeException{
    public InternalServiceException(String message) {
        super(message);
    }
}
