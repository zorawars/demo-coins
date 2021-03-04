package com.example.coins.receiver.repository;

/**
 * The various states applicable to an invoice.
 */
public enum InvoiceState {
    /** Due when invoice is first created */
    DUE("DUE"),
    /** Expires when invoice is past expiry date */
    EXPIRED("EXPIRED"),
    /** When invoice amount is not paid in full */
    PARTIALLY_PAID("PARTIALLY_PAID"),
    /** When invoice amount is paid in full */
    PAID("PAID"),
    /** When extra amount is paid for the invoice than is due */
    OVER_PAID("OVER_PAID");

    private final String code;

    InvoiceState(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
