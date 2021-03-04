package com.example.coins.receiver.service;

import com.example.coins.receiver.repository.Invoice;
import com.example.coins.receiver.repository.InvoiceRepository;
import com.example.coins.receiver.repository.InvoiceState;
import com.example.coins.receiver.repository.Payment;
import com.example.coins.receiver.web.Currency;
import com.example.coins.receiver.web.Money;
import com.example.coins.wallet.CoinWallet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final CoinWallet receiverWallet;
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(CoinWallet receiverWallet, InvoiceRepository invoiceRepository) {
        this.receiverWallet = receiverWallet;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice createInvoice(Money invoiceAmount) {
        Invoice invoice = new Invoice();
        invoice.setAmount(invoiceAmount.getAmount());
        invoice.setCurrencyCd(invoiceAmount.getCurrency().getCode());
        invoice.setAmountOwed(invoiceAmount.getAmount());
        invoice.setOwedCurrencyCd(invoiceAmount.getCurrency().getCode());
        invoice.setExpiryDate(LocalDateTime.now().plusDays(10));
        String paymentAddress = receiverWallet.getPaymentAddress();
        invoice.setPayAddress(paymentAddress);
        invoice.setState(InvoiceState.DUE.getCode());
        Invoice createdInvoice = invoiceRepository.save(invoice);
        return createdInvoice;
    }

    @Override
    public List<Invoice> getInvoices() {
        return Collections.unmodifiableList(invoiceRepository.findAll());
    }

    @Override
    public Invoice getInvoice(long invoiceId, String payAddress) throws OperationException {
        Invoice invoice = invoiceRepository.findInvoiceByInvoiceIdAndPayAddress(invoiceId, payAddress);
        if (invoice == null) {
            throw new OperationException(
                    String.format("invoice does not exist for %d and %s", invoiceId, payAddress));
        }
        return invoice;
    }

    @Override
    public String getPayAddress(long invoiceId) throws OperationException {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new OperationException("invoice not found for %d" + invoiceId));
        return invoice.getPayAddress();
    }

    @Override
    public boolean validateInvoiceDetails(long invoiceId, String payAddress) throws OperationException {
        if (!invoiceRepository.existsByInvoiceIdAndPayAddress(invoiceId, payAddress)) {
            throw new OperationException(
                    String.format("invoice does not exist for %d and %s", invoiceId, payAddress));
        }
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        InvoiceState state = InvoiceState.valueOf(invoice.getState());
        if (state == InvoiceState.EXPIRED || LocalDate.now().isAfter(invoice.getExpiryDate().toLocalDate())) {
            throw new OperationException(
                    String.format("invoice has expired for %d and %s", invoiceId, payAddress));
        }
        return true;
    }

    @Override
    public void receivePayment(Payment payment) {
        Invoice invoice = invoiceRepository.findById(payment.getInvoiceId()).get();
        BigDecimal owedAmount = invoice.getAmount().subtract(payment.getAmount());
        invoice.setAmountOwed(owedAmount);
        invoice.setOwedCurrencyCd(Currency.BTC.getCode());
        if (owedAmount.signum() == 0) {
            invoice.setState(InvoiceState.PAID.getCode());
        } else if (owedAmount.signum() > 0) {
            invoice.setState(InvoiceState.PARTIALLY_PAID.getCode());
        } else {
            invoice.setState(InvoiceState.OVER_PAID.getCode());
        }
        invoiceRepository.save(invoice);
    }
}
