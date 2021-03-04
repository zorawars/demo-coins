package com.example.coins.receiver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    List<Invoice> findAll();

    Invoice findInvoiceByInvoiceIdAndPayAddress(Long invoiceId, String payAddress);

    boolean existsByInvoiceIdAndPayAddress(Long invoiceId, String payAddress);

}
