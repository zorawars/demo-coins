package com.example.coins.receiver.repository;

/**
 * The various states applicable to a Payment.
 */
public enum PaymentState {
    /**
     * State when payment is received
     */
    RECEIVED("RECEIVED"),
    /**
     * State when receiver has been able to reconcile payment after receiving it
     */
    RECONCILED("RECONCILED"),
    /**
     * State when payment is rejected for any reason
     */
    REJECTED("REJECTED"),
    /**
     * State when payment has been confirmed after being reconciled
     */
    CONFIRMED("CONFIRMED");

    private final String state;

    PaymentState(String state) {
        this.state = state;
    }

    public String getCode() {
        return this.state;
    }

}
