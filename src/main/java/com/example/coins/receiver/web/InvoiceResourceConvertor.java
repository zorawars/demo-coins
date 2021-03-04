package com.example.coins.receiver.web;

import com.example.coins.receiver.repository.Invoice;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Convertor to transform repository artifacts to web resources.
 * <p>
 *     The example here is for Invoices.
 * </p>
 */
@Component
public final class InvoiceResourceConvertor {

    public List<InvoiceResource> convert(List<Invoice> invoices) {
        return invoices.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public InvoiceResource convert(Invoice invoice) {
        String invoiceDate = invoice.getInvoiceDate().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate expiryDate = invoice.getExpiryDate().toLocalDate();
        String paymentAmount = String.format("%f %s", invoice.getAmount(), invoice.getCurrencyCd());
        String owedAmount = String.format("%f %s", invoice.getAmountOwed(), invoice.getOwedCurrencyCd());
        return new InvoiceResource(invoice.getInvoiceId(),
                invoiceDate,
                expiryDate,
                paymentAmount,
                invoice.getPayAddress(),
                owedAmount, invoice.getState());
    }

}
