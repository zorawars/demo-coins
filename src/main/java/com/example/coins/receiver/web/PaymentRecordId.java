package com.example.coins.receiver.web;

public class PaymentRecordId {
    private final long invoiceId;
    private final long paymentId;

    public PaymentRecordId(long invoiceId, long paymentId) {
        this.invoiceId = invoiceId;
        this.paymentId = paymentId;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    @Override
    public String toString() {
        return "PaymentRecordId{" +
                "invoiceId=" + invoiceId +
                ", paymentId=" + paymentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentRecordId)) return false;

        PaymentRecordId that = (PaymentRecordId) o;

        if (invoiceId != that.invoiceId) return false;
        return paymentId == that.paymentId;
    }

    @Override
    public int hashCode() {
        int result = (int) (invoiceId ^ (invoiceId >>> 32));
        result = 31 * result + (int) (paymentId ^ (paymentId >>> 32));
        return result;
    }
}
