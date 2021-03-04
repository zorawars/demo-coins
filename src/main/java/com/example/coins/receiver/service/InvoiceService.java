package com.example.coins.receiver.service;

import com.example.coins.receiver.repository.Invoice;
import com.example.coins.receiver.repository.Payment;
import com.example.coins.receiver.web.Money;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Invoicing related operations.
 */
@Service
public interface InvoiceService {

    /** Create invoice when provided an amount */
    Invoice createInvoice(Money amount);

    /** Retrieve invoices. This is known to be inefficient as we would not fetch everything into memory */
    List<Invoice> getInvoices();

    /** Retrieve an invoice provided it's ID and Payment address */
    Invoice getInvoice(long invoiceId, String payAddress) throws OperationException;

    /** Get Payment address for an invoice */
    String getPayAddress(long invoiceId) throws OperationException;

    /** Validate invoice for provided details */
    boolean validateInvoiceDetails(long invoiceId, String payAddress) throws OperationException;

    /** Manage invoice when payment is confirmed */
    void receivePayment(Payment payment);

}
