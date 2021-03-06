package org.example.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.math3.util.Precision;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Schedule {

    private UUID schedule_id;
    private int limitCredit;
    private double interestRate;
    private int loanAmountFieldText;
    private int yearForPayCredit;

    public Schedule(UUID schedule_id, int limitCredit, double interestRate, int loanAmountFieldText, int yearForPayCredit) {
        this.schedule_id = schedule_id;
        this.limitCredit = limitCredit;
        this.interestRate = interestRate;
        this.loanAmountFieldText = loanAmountFieldText;
        this.yearForPayCredit = yearForPayCredit;
    }

    public UUID getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(UUID schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getLimitCredit() {
        return limitCredit;
    }

    public void setLimitCredit(int limitCredit) {
        this.limitCredit = limitCredit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanAmountFieldText() {
        return loanAmountFieldText;
    }

    public void setLoanAmountFieldText(int loanAmountFieldText) {
        this.loanAmountFieldText = loanAmountFieldText;
    }

    public int getYearForPayCredit() {
        return yearForPayCredit;
    }

    public void setYearForPayCredit(int yearForPayCredit) {
        this.yearForPayCredit = yearForPayCredit;
    }

    public int convertToMonth() {
        return yearForPayCredit * 12;
    }

    public ObservableList<DataSchedule> creditPayment() {

        ObservableList<DataSchedule> dataSchedules = FXCollections.observableArrayList();

        double loanPerMonth = (interestRate / 100) / 12;
        double countPerMonth = Precision.round(((loanAmountFieldText * loanPerMonth) / (1 - (Math.pow(1 + loanPerMonth, -convertToMonth())))), 2);
        double count = countPerMonth * convertToMonth();

        double creditAll = loanAmountFieldText;

        LocalDate dateCurrent = LocalDate.now();

        for (int i = 1; i <= convertToMonth(); i++) {

            double teloProzenta = Precision.round((((creditAll * interestRate) / 12) / 100), 2);
            double teloKredita = Precision.round((countPerMonth - teloProzenta), 2);
            double amount = Precision.round((creditAll - teloKredita), 2);

            if (i == convertToMonth()) {
                amount = 0;
            }

            creditAll = amount;
            String month = String.valueOf(dateCurrent);
            dataSchedules.add(new DataSchedule(month, countPerMonth, teloKredita, teloProzenta));
            dateCurrent = dateCurrent.plusMonths(1);

        }
        return dataSchedules;
    }

}
