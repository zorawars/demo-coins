package com.example.coins.receiver.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;

@Configuration
public class BlockchainConfig {

    @Bean
    public BitcoinJSONRPCClient client() {
        return new BitcoinJSONRPCClient(BitcoinJSONRPCClient.DEFAULT_JSONRPC_REGTEST_URL);
    }

}
