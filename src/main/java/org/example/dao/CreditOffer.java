package org.example.dao;

import java.util.UUID;

public class CreditOffer {

    private UUID id;
    private String clientNameTelephoneEmailPassport;
    private String bankOffer;
    private int creditAmount;
    private int year;

    public CreditOffer(UUID id, String clientNameTelephoneEmailPassport, String bankOffer, int creditAmount, int year) {
        this.id = id;
        this.clientNameTelephoneEmailPassport = clientNameTelephoneEmailPassport;
        this.bankOffer = bankOffer;
        this.creditAmount = creditAmount;
        this.year = year;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientNameTelephoneEmailPassport() {
        return clientNameTelephoneEmailPassport;
    }

    public void setClientNameTelephoneEmailPassport(String clientNameTelephoneEmailPassport) {
        this.clientNameTelephoneEmailPassport = clientNameTelephoneEmailPassport;
    }

    public String getBankOffer() {
        return bankOffer;
    }

    public void setBankOffer(String bankOffer) {
        this.bankOffer = bankOffer;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
