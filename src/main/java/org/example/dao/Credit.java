package org.example.dao;

import java.util.UUID;

public class Credit {

    private UUID creditId;
    private int creditLimit;
    private int interestRate;

    public Credit(UUID creditId, int creditLimit, int interestRate) {
        this.creditId = creditId;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public UUID getCreditId() {
        return creditId;
    }

    public void setCreditId(UUID creditId) {
        this.creditId = creditId;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }
}
