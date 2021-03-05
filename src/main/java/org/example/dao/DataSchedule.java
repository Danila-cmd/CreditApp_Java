package org.example.dao;

public class DataSchedule {

    private int month;
    private double summaPlatezha;
    private double teloKredita;
    private double teloProzentov;

    public DataSchedule(int month, double summaPlatezha, double teloKredita, double teloProzentov) {
        this.month = month;
        this.summaPlatezha = summaPlatezha;
        this.teloKredita = teloKredita;
        this.teloProzentov = teloProzentov;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getSummaPlatezha() {
        return summaPlatezha;
    }

    public void setSummaPlatezha(double summaPlatezha) {
        this.summaPlatezha = summaPlatezha;
    }

    public double getTeloKredita() {
        return teloKredita;
    }

    public void setTeloKredita(double teloKredita) {
        this.teloKredita = teloKredita;
    }

    public double getTeloProzentov() {
        return teloProzentov;
    }

    public void setTeloProzentov(double teloProzentov) {
        this.teloProzentov = teloProzentov;
    }

    @Override
    public String toString() {
        return "DataSchedule{" +
                "month=" + month +
                ", summaPlatezha=" + summaPlatezha +
                ", teloKredita=" + teloKredita +
                ", teloProzentov=" + teloProzentov +
                '}';
    }
}
