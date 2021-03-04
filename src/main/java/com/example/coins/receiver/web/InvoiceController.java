package com.example.coins.receiver.web;

import com.example.coins.receiver.repository.Invoice;
import com.example.coins.receiver.service.InvoiceService;
import com.example.coins.receiver.service.OperationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "invoices")
@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceResourceConvertor invoiceResourceConvertor;

    public InvoiceController(InvoiceService invoiceService, InvoiceResourceConvertor invoiceResourceConvertor) {
        this.invoiceService = invoiceService;
        this.invoiceResourceConvertor = invoiceResourceConvertor;
    }

    @GetMapping(value = "/receiver/invoices",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InvoiceResource>> invoices() {
        List<Invoice> invoices = invoiceService.getInvoices();
        List<InvoiceResource> resources = invoiceResourceConvertor.convert(invoices);
        return ResponseEntity.ok(resources);
    }

    @PostMapping(value = "/receiver/invoices",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<InvoiceResource> createInvoice(@RequestBody Money invoiceAmount) {
        Invoice invoice = invoiceService.createInvoice(invoiceAmount);
        InvoiceResource invoiceResource = invoiceResourceConvertor.convert(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceResource);
    }

    @GetMapping(value = "/receiver/invoices/{invoiceId}/{address}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<InvoiceResource> getPaymentDetails(
            @ApiParam(required = true) @PathVariable("invoiceId") long invoiceId,
            @ApiParam(required = true) @PathVariable("address") String address) {
        try {
            Invoice invoice = invoiceService.getInvoice(invoiceId, address);
            InvoiceResource invoiceResource = invoiceResourceConvertor.convert(invoice);
            return ResponseEntity.ok(invoiceResource);
        } catch (OperationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
