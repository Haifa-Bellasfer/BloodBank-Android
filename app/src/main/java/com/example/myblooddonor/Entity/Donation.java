package com.example.myblooddonor.Entity;

public class Donation {
    private int id ;
    private String date_donation;
    private int id_user_donation;
    private int id_request;

    public Donation(int id, String date_donation, int id_user_donation, int id_request) {
        this.id = id;
        this.date_donation = date_donation;
        this.id_user_donation = id_user_donation;
        this.id_request = id_request;
    }

    public Donation(String date_donation, int id_user_donation, int id_request) {
        this.date_donation = date_donation;
        this.id_user_donation = id_user_donation;
        this.id_request = id_request;
    }

    public Donation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_donation() {
        return date_donation;
    }

    public void setDate_donation(String date_donation) {
        this.date_donation = date_donation;
    }

    public int getId_user_donation() {
        return id_user_donation;
    }

    public void setId_user_donation(int id_user_donation) {
        this.id_user_donation = id_user_donation;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", date_donation='" + date_donation + '\'' +
                ", id_user_donation=" + id_user_donation +
                ", id_request=" + id_request +
                '}';
    }
}
