package com.example.coins.wallet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;

import java.net.MalformedURLException;

@Configuration
public class WalletConfig {

    @Bean
    public BitcoinJSONRPCClient receiverClient() throws MalformedURLException {
        String walletUrl = String.format("%swallet/receiver", BitcoinJSONRPCClient.DEFAULT_JSONRPC_REGTEST_URL);
        return new BitcoinJSONRPCClient(walletUrl);
    }

    @Bean
    public CoinWallet receiverWallet(BitcoinJSONRPCClient receiverClient) {
        return new CoinWallet(receiverClient);
    }

    @Bean
    public BitcoinJSONRPCClient senderClient() throws MalformedURLException {
        String walletUrl = String.format("%swallet/sender", BitcoinJSONRPCClient.DEFAULT_JSONRPC_REGTEST_URL);
        return new BitcoinJSONRPCClient(walletUrl);
    }

    @Bean
    public CoinWallet senderWallet(BitcoinJSONRPCClient senderClient) {
        return new CoinWallet(senderClient);
    }
}
