package com.example.coins.receiver.web;

import java.time.LocalDate;

/**
 * Web resource representation of an invoice.
 */
public class InvoiceResource {
    private final long invoiceId;
    private final String invoiceDate;
    private final LocalDate expiryDate;
    private final String paymentAmount;
    private final String paymentAddress;
    private final String owedAmount;
    private final String state;

    public InvoiceResource(long invoiceId, String invoiceDate, LocalDate expiryDate,
                           String paymentAmount, String paymentAddress, String owedAmount, String state) {
        this.invoiceId = invoiceId;
        this.invoiceDate = invoiceDate;
        this.expiryDate = expiryDate;
        this.paymentAmount = paymentAmount;
        this.paymentAddress = paymentAddress;
        this.owedAmount = owedAmount;
        this.state = state;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentAddress() {
        return paymentAddress;
    }

    public String getOwedAmount() {
        return owedAmount;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "InvoiceResource{" +
                "invoiceId=" + invoiceId +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", expiryDate=" + expiryDate +
                ", paymentAmount='" + paymentAmount + '\'' +
                ", paymentAddress='" + paymentAddress + '\'' +
                ", owedAmount='" + owedAmount + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
