package com.example.coins.receiver.repository;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    /*
    Spring Data JDBC does not handle composite keys automatically so we follow this approach.
     */
    @Id
    private Long paymentId;
    private Long invoiceId;
    private LocalDateTime paymentDate;
    private BigDecimal amount;
    private String currencyCd;
    private String transactionId;
    private String state;

    public Payment() {
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCd() {
        return currencyCd;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", invoiceId=" + invoiceId +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", currencyCd='" + currencyCd + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
