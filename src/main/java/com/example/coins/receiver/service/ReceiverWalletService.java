package com.example.coins.receiver.service;

import com.example.coins.wallet.CoinWallet;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service abstraction for Receiver's wallet related operations.
 */
@Service
public final class ReceiverWalletService {
    private final CoinWallet receiverWallet;

    public ReceiverWalletService(CoinWallet receiverWallet) {
        this.receiverWallet = receiverWallet;
    }

    public BigDecimal getBalance() {
        return receiverWallet.getBalance();
    }
}
