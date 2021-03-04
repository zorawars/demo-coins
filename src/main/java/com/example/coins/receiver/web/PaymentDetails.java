package com.example.coins.receiver.web;

import java.util.Objects;

/**
 * Represents payment details that are sent from Sender to Receiver.
 */
public class PaymentDetails {
    private final long invoiceId;
    private final String paymentAddress;
    private final Money paymentAmount;
    private final String transactionId;

    public PaymentDetails(long invoiceId, String paymentAddress, Money paymentAmount, String transactionId) {
        Objects.requireNonNull(paymentAddress);
        Objects.requireNonNull(paymentAmount);
        Objects.requireNonNull(transactionId);
        this.invoiceId = invoiceId;
        this.paymentAddress = paymentAddress;
        this.paymentAmount = paymentAmount;
        this.transactionId = transactionId;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public String getPaymentAddress() {
        return paymentAddress;
    }

    public Money getPaymentAmount() {
        return paymentAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "invoiceId=" + invoiceId +
                ", paymentAddress='" + paymentAddress + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDetails)) return false;

        PaymentDetails that = (PaymentDetails) o;

        if (invoiceId != that.invoiceId) return false;
        if (!paymentAddress.equals(that.paymentAddress)) return false;
        if (!paymentAmount.equals(that.paymentAmount)) return false;
        return transactionId.equals(that.transactionId);
    }

    @Override
    public int hashCode() {
        int result = (int) (invoiceId ^ (invoiceId >>> 32));
        result = 31 * result + paymentAddress.hashCode();
        result = 31 * result + paymentAmount.hashCode();
        result = 31 * result + transactionId.hashCode();
        return result;
    }
}
