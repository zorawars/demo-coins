package com.example.coins.receiver.web;

/**
 * Supported Currencies.
 */
public enum Currency {
    BTC("BTC", "Bitcoin");

    private final String code;
    private final String name;

    Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String currencyName() {
        return this.name;
    }

    public boolean isValid(String code) {
        return this.code.equals(code);
    }

    @Override
    public String toString() {
        return code;
    }
}
