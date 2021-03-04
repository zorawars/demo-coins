package com.example.coins.receiver.web;

import com.example.coins.receiver.service.ReceiverWalletService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Api(tags = "receiver-wallet")
@RestController
public class ReceiverWalletController {

    private final ReceiverWalletService receiverWalletService;

    public ReceiverWalletController(ReceiverWalletService receiverWalletService) {
        this.receiverWalletService = receiverWalletService;
    }

    @GetMapping(value = "/receiver/wallet/balance")
    @ResponseBody
    public ResponseEntity<BigDecimal> balance() {
        return ResponseEntity.ok(receiverWalletService.getBalance());
    }

}
