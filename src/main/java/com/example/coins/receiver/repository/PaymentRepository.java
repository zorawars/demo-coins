package com.example.coins.receiver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findAllByInvoiceId(Long invoiceId);

    Payment findByInvoiceIdAndPaymentId(Long invoiceId, Long paymentId);

}
