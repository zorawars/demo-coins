package com.example.coins.sender;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents the payment amount and address to pay for the Sender's wallet.
 */
public class PaymentRequest {
    private final BigDecimal amount;
    private final String address;

    public PaymentRequest(BigDecimal amount, String address) {
        Objects.requireNonNull(amount);
        Objects.requireNonNull(address);
        this.amount = amount;
        this.address = address;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "amount=" + amount +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentRequest)) return false;

        PaymentRequest that = (PaymentRequest) o;

        if (!amount.equals(that.amount)) return false;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
