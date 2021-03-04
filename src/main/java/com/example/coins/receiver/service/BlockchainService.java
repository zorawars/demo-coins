package com.example.coins.receiver.service;

import org.springframework.stereotype.Service;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;

/**
 * Blockchain service to perform operations that are independent of the wallet.
 */
@Service
public final class BlockchainService {

    private final BitcoinJSONRPCClient client;

    public BlockchainService(BitcoinJSONRPCClient client) {
        this.client = client;
    }

    public boolean confirm(String address) {
        client.generateToAddress(1, address);
        int confirmations = 1;
        return confirmations >= 1;
    }
}
