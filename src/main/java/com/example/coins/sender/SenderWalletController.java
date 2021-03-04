package com.example.coins.sender;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Api(tags = "sender-wallet")
@RestController
public class SenderWalletController {

    private final SenderWalletService senderWalletService;

    public SenderWalletController(SenderWalletService senderWalletService) {
        this.senderWalletService = senderWalletService;
    }

    @GetMapping(value = "/sender/wallet/balance")
    @ResponseBody
    public ResponseEntity<BigDecimal> balance() {
        return ResponseEntity.ok(senderWalletService.getBalance());
    }

    @PostMapping(value = "/sender/wallet",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PaymentInfo> spend(@RequestBody PaymentRequest paymentRequest) {
        PaymentInfo paymentInfo = senderWalletService.spend(paymentRequest.getAddress(), paymentRequest.getAmount());
        return ResponseEntity.ok(paymentInfo);
    }

}
