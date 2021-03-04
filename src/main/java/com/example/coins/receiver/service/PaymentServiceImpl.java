package com.example.coins.receiver.service;

import com.example.coins.receiver.repository.Payment;
import com.example.coins.receiver.repository.PaymentRepository;
import com.example.coins.receiver.repository.PaymentState;
import com.example.coins.receiver.web.Money;
import com.example.coins.receiver.web.PaymentDetails;
import com.example.coins.wallet.CoinWallet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final InvoiceService invoiceService;
    private final CoinWallet receiverWallet;
    private final BlockchainService blockchainService;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(InvoiceService invoiceService, CoinWallet receiverWallet, BlockchainService blockchainService, PaymentRepository paymentRepository) {
        this.invoiceService = invoiceService;
        this.receiverWallet = receiverWallet;
        this.blockchainService = blockchainService;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getPayments(long invoiceId) {
        return Collections.unmodifiableList(paymentRepository.findAllByInvoiceId(invoiceId));
    }

    @Override
    public Payment receive(PaymentDetails paymentDetails) throws OperationException {
        invoiceService.validateInvoiceDetails(paymentDetails.getInvoiceId(), paymentDetails.getPaymentAddress());
        Payment payment = record(paymentDetails);
        return payment;
    }

    private Payment record(PaymentDetails details) {
        Payment payment = new Payment();
        payment.setInvoiceId(details.getInvoiceId());
        Money paymentAmount = details.getPaymentAmount();
        payment.setAmount(paymentAmount.getAmount());
        payment.setCurrencyCd(paymentAmount.getCurrency().getCode());
        payment.setTransactionId(details.getTransactionId());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setState(PaymentState.RECEIVED.getCode());
        Payment recordedPayment = paymentRepository.save(payment);
        return recordedPayment;
    }

    @Override
    public Payment reconcile(long invoiceId, long paymentId) throws OperationException {
        Payment payment = find(invoiceId, paymentId);
        PaymentState state = PaymentState.valueOf(payment.getState());
        if (state != PaymentState.RECEIVED) {
            throw new OperationException(
                    String.format("payment for (%d, %d) is not in %s state", invoiceId, paymentId, PaymentState.RECEIVED));
        }
        return reconcile(payment);
    }

    private Payment find(long invoiceId, long paymentId) throws OperationException {
        Payment payment = paymentRepository.findByInvoiceIdAndPaymentId(invoiceId, paymentId);
        if (payment == null) {
            throw new OperationException(String.format("payment not found for (%d, %d)", invoiceId, paymentId));
        }
        return payment;
    }

    private Payment reconcile(Payment payment) {
        boolean match = receiverWallet.reconcileAmount(payment.getAmount(), payment.getTransactionId());
        PaymentState state = match ? PaymentState.RECONCILED : PaymentState.REJECTED;
        payment.setState(state.getCode());
        return paymentRepository.save(payment);
    }

    @Override
    public boolean confirm(long invoiceId, long paymentId) throws OperationException {
        Payment payment = find(invoiceId, paymentId);
        PaymentState state = PaymentState.valueOf(payment.getState());
        if (state != PaymentState.RECONCILED) {
            throw new OperationException(
                    String.format("payment for (%d, %d) is not in %s state", invoiceId, paymentId, PaymentState.RECONCILED));
        }
        String payAddress = invoiceService.getPayAddress(invoiceId);
        return confirm(payment, payAddress);
    }

    private boolean confirm(Payment payment, String payAddress) {
        boolean confirmed = blockchainService.confirm(payAddress);
        if (confirmed) {
            payment.setState(PaymentState.CONFIRMED.getCode());
            Payment confirmedPayment = paymentRepository.save(payment);
            invoiceService.receivePayment(confirmedPayment);
        }
        return confirmed;
    }

}
