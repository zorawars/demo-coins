package com.example.coins.receiver.repository;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Invoice {
    @Id
    private Long invoiceId;
    private LocalDateTime invoiceDate;
    private LocalDateTime expiryDate;
    private BigDecimal amount;
    private String currencyCd;
    private String payAddress;
    private BigDecimal amountOwed;
    private String owedCurrencyCd;
    private String state;

    public Invoice() {
        this.invoiceDate = LocalDateTime.now();
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
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

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress;
    }

    public BigDecimal getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(BigDecimal amountOwed) {
        this.amountOwed = amountOwed;
    }

    public String getOwedCurrencyCd() {
        return owedCurrencyCd;
    }

    public void setOwedCurrencyCd(String owedCurrencyCd) {
        this.owedCurrencyCd = owedCurrencyCd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", invoiceDate=" + invoiceDate +
                ", expiryDate=" + expiryDate +
                ", amount=" + amount +
                ", currencyCd='" + currencyCd + '\'' +
                ", payAddress='" + payAddress + '\'' +
                ", amountOwed=" + amountOwed +
                ", owedCurrencyCd='" + owedCurrencyCd + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
