package org.example.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.math3.util.Precision;

import java.time.LocalDate;
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

            double bodyPercent = Precision.round((((creditAll * interestRate) / 12) / 100), 2);
            double bodyCredit = Precision.round((countPerMonth - bodyPercent), 2);
            double amount = Precision.round((creditAll - bodyCredit), 2);

            if (i == convertToMonth()) {
                amount = 0;
            }

            creditAll = amount;
            String month = String.valueOf(dateCurrent);
            dataSchedules.add(new DataSchedule(month, countPerMonth, bodyCredit, bodyPercent));
            dateCurrent = dateCurrent.plusMonths(1);

        }
        return dataSchedules;
    }

}
