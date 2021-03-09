package org.example.dao;

public class DataSchedule {

    private String month;
    private double amountOfPayment;
    private double loanBody;
    private double bodyPercent;

    public DataSchedule(String month, double amountOfPayment, double loanBody, double bodyPercent) {
        this.month = month;
        this.amountOfPayment = amountOfPayment;
        this.loanBody = loanBody;
        this.bodyPercent = bodyPercent;
    }

    @Override
    public String toString() {
        return "DataSchedule{" +
                "month=" + month +
                ", amountOfPayment=" + amountOfPayment +
                ", loanBody=" + loanBody +
                ", bodyPercent=" + bodyPercent +
                '}';
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmountOfPayment() {
        return amountOfPayment;
    }

    public void setAmountOfPayment(double amountOfPayment) {
        this.amountOfPayment = amountOfPayment;
    }

    public double getLoanBody() {
        return loanBody;
    }

    public void setLoanBody(double loanBody) {
        this.loanBody = loanBody;
    }

    public double getBodyPercent() {
        return bodyPercent;
    }

    public void setBodyPercent(double bodyPercent) {
        this.bodyPercent = bodyPercent;
    }
}
