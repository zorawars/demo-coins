package com.example.coins.wallet;

import com.example.coins.receiver.web.Money;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import java.math.BigDecimal;

/**
 * Abstraction for a wallet that interacts with the underlying Bitcoin wallet using JSON RPC API client.
 * <p>
 * This allows to represent wallets for both Senders and Receivers by customizing the JSON RPC client.
 * </p>
 *
 * @see WalletConfig
 */
public class CoinWallet {

    private final BitcoinJSONRPCClient client;

    public CoinWallet(BitcoinJSONRPCClient client) {
        this.client = client;
    }

    public BigDecimal getBalance() {
        return client.getWalletInfo().balance();
    }

    public String getPaymentAddress() {
        String paymentAddress = client.getNewAddress();
        return paymentAddress;
    }

    public String sendMoney(String paymentAddress, Money paymentAmount) {
        return client.sendToAddress(paymentAddress, paymentAmount.getAmount(), "", "");
    }

    public String send(String paymentAddress, BigDecimal amount) {
        String message = String.format("Sender sends %f BTC to Receiver", amount);
        return client.sendToAddress(paymentAddress, amount, message, "Receiver");
    }

    public boolean reconcileAmount(BigDecimal amount, String transactionId) {
        BitcoindRpcClient.Transaction transaction = client.getTransaction(transactionId);
        BigDecimal transactionAmount = transaction.amount();
        //transaction.address() is null so cannot match addresses
        return amount.compareTo(transactionAmount) == 0;
    }

}
