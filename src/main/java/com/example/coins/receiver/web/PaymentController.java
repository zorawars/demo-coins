package com.example.coins.receiver.web;

import com.example.coins.receiver.repository.Payment;
import com.example.coins.receiver.service.OperationException;
import com.example.coins.receiver.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "payments")
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/receiver/invoices/{invoiceId}/payments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Payment>> payments(
            @ApiParam(required = true) @PathVariable("invoiceId") long invoiceId) {
        List<Payment> payments = paymentService.getPayments(invoiceId);
        return ResponseEntity.ok(payments);
    }

    @PostMapping(value = "/receiver/invoices/{invoiceId}/payments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PaymentRecordId> createPayment(
            @ApiParam(required = true) @PathVariable("invoiceId") long invoiceId,
            @RequestBody PaymentDetails paymentDetails) {
        try {
            Payment payment = paymentService.receive(paymentDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentRecordId(payment.getInvoiceId(), payment.getPaymentId()));
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping(value = "/receiver/invoices/{invoiceId}/payments/{paymentId}/reconcile")
    @ResponseBody
    public ResponseEntity<PaymentRecordId> reconcile(
            @ApiParam(required = true) @PathVariable("invoiceId") long invoiceId,
            @ApiParam(required = true) @PathVariable("paymentId") long paymentId) {
        try {
            Payment payment = paymentService.reconcile(invoiceId, paymentId);
            return ResponseEntity.ok(new PaymentRecordId(payment.getInvoiceId(), payment.getPaymentId()));
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping(value = "/receiver/invoices/{invoiceId}/payments/{paymentId}/confirm")
    @ResponseBody
    public ResponseEntity<Void> confirm(
            @ApiParam(required = true) @PathVariable("invoiceId") long invoiceId,
            @ApiParam(required = true) @PathVariable("paymentId") long paymentId) {
        try {
            paymentService.confirm(invoiceId, paymentId);
            return ResponseEntity.ok(null);
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
