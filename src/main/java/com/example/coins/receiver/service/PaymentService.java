package com.example.coins.receiver.service;

import com.example.coins.receiver.repository.Payment;
import com.example.coins.receiver.web.PaymentDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Payments related operations.
 */
@Service
public interface PaymentService {

    /**
     * Get Payments given the invoice ID
     */
    List<Payment> getPayments(long invoiceId);

    /**
     * Receive payment details
     */
    Payment receive(PaymentDetails paymentDetails) throws OperationException;

    /**
     * Reconcile payment for an invoice after the invoice's payment details have been received
     */
    Payment reconcile(long invoiceId, long paymentId) throws OperationException;

    /**
     * Confirm payment for an invoice after it has been reconciled
     */
    boolean confirm(long invoiceId, long paymentId) throws OperationException;

}
