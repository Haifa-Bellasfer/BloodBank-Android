package com.example.myblooddonor.Entity;

import java.util.Date;

public class Request {
    private int id ;
    private String firstNameRequest;
    private String lastNameRequest;
    private String cityRequest_lan;
    private String cityRequest_lon;
    private String bloodTypeRequest;
    private String description;
    private int user_id;
    private String phoneRequest;
    private String date_request;
    private int count_donation;
    private User user ;

    public Request(int id, String firstNameRequest, String lastNameRequest, String cityRequest_lan, String cityRequest_lon, String bloodTypeRequest, String description, int user_id, String phoneRequest, String date_request, int count_donation, User user) {
        this.id = id;
        this.firstNameRequest = firstNameRequest;
        this.lastNameRequest = lastNameRequest;
        this.cityRequest_lan = cityRequest_lan;
        this.cityRequest_lon = cityRequest_lon;
        this.bloodTypeRequest = bloodTypeRequest;
        this.description = description;
        this.user_id = user_id;
        this.phoneRequest = phoneRequest;
        this.date_request = date_request;
        this.count_donation = count_donation;
        this.user = user;
    }

    public Request(String firstNameRequest, String lastNameRequest, String cityRequest_lan, String cityRequest_lon, String bloodTypeRequest, String description, int user_id, String phoneRequest, String date_request, int count_donation, User user) {
        this.firstNameRequest = firstNameRequest;
        this.lastNameRequest = lastNameRequest;
        this.cityRequest_lan = cityRequest_lan;
        this.cityRequest_lon = cityRequest_lon;
        this.bloodTypeRequest = bloodTypeRequest;
        this.description = description;
        this.user_id = user_id;
        this.phoneRequest = phoneRequest;
        this.date_request = date_request;
        this.count_donation = count_donation;
        this.user = user;
    }

    public Request() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstNameRequest() {
        return firstNameRequest;
    }

    public void setFirstNameRequest(String firstNameRequest) {
        this.firstNameRequest = firstNameRequest;
    }

    public String getLastNameRequest() {
        return lastNameRequest;
    }

    public void setLastNameRequest(String lastNameRequest) {
        this.lastNameRequest = lastNameRequest;
    }

    public String getCityRequest_lan() {
        return cityRequest_lan;
    }

    public void setCityRequest_lan(String cityRequest_lan) {
        this.cityRequest_lan = cityRequest_lan;
    }

    public String getCityRequest_lon() {
        return cityRequest_lon;
    }

    public void setCityRequest_lon(String cityRequest_lon) {
        this.cityRequest_lon = cityRequest_lon;
    }

    public String getBloodTypeRequest() {
        return bloodTypeRequest;
    }

    public void setBloodTypeRequest(String bloodTypeRequest) {
        this.bloodTypeRequest = bloodTypeRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhoneRequest() {
        return phoneRequest;
    }

    public void setPhoneRequest(String phoneRequest) {
        this.phoneRequest = phoneRequest;
    }

    public String getDate_request() {
        return date_request;
    }

    public void setDate_request(String date_request) {
        this.date_request = date_request;
    }

    public int getCount_donation() {
        return count_donation;
    }

    public void setCount_donation(int count_donation) {
        this.count_donation = count_donation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", firstNameRequest='" + firstNameRequest + '\'' +
                ", lastNameRequest='" + lastNameRequest + '\'' +
                ", cityRequest_lan='" + cityRequest_lan + '\'' +
                ", cityRequest_lon='" + cityRequest_lon + '\'' +
                ", bloodTypeRequest='" + bloodTypeRequest + '\'' +
                ", description='" + description + '\'' +
                ", user_id=" + user_id +
                ", phoneRequest='" + phoneRequest + '\'' +
                ", date_request='" + date_request + '\'' +
                ", count_donation=" + count_donation +
                ", user=" + user +
                '}';
    }
}
