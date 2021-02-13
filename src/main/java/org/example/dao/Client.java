package org.example.dao;

import java.util.UUID;

public class Client {

    private UUID clientId;
    private String nameSerName;
    private String phoneNumber;
    private String email;
    private String passportNumber;

    public Client(UUID id, String nameSerName, String phoneNumber, String email, String passportNumber) {
        this.clientId = id;
        this.nameSerName = nameSerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportNumber = passportNumber;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getNameSerName() {
        return nameSerName;
    }

    public void setNameSerName(String nameSerName) {
        this.nameSerName = nameSerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPasswordNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nameSerName='" + nameSerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
