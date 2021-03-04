package com.example.coins.sender;

import com.example.coins.wallet.CoinWallet;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service abstraction for Sender's wallet related operations.
 */
@Service
public final class SenderWalletService {

    private final CoinWallet senderWallet;

    public SenderWalletService(CoinWallet senderWallet) {
        this.senderWallet = senderWallet;
    }

    public BigDecimal getBalance() {
        return senderWallet.getBalance();
    }

    public PaymentInfo spend(String address, BigDecimal amount) {
        String transactionId = senderWallet.send(address, amount);
        return new PaymentInfo(amount, transactionId);
    }
}
