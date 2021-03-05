package org.example.dao;

public class CreditOffer {

    private Client client;
    private Credit credit;
    private int creditValue;

    public CreditOffer(Client client, Credit credit, int creditValue) {
        this.client = client;
        this.credit = credit;
        this.creditValue = creditValue;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public int getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(int creditValue) {
        this.creditValue = creditValue;
    }

}
