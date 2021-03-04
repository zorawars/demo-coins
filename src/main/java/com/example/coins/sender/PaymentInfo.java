package com.example.coins.sender;

import java.math.BigDecimal;

/**
 * The payment information post a spend from the Sender's wallet.
 */
public class PaymentInfo {
    private final BigDecimal amount;
    private final String transactionId;

    public PaymentInfo(BigDecimal amount, String transactionId) {
        this.amount = amount;
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "amount=" + amount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentInfo)) return false;

        PaymentInfo that = (PaymentInfo) o;

        if (!amount.equals(that.amount)) return false;
        return transactionId.equals(that.transactionId);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + transactionId.hashCode();
        return result;
    }
}
