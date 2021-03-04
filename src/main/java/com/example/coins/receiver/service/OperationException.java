package com.example.coins.receiver.service;

/**
 * General exception class to handle known exceptions.
 */
public class OperationException extends Exception {
    public OperationException(String message) {
        super(message);
    }
}
