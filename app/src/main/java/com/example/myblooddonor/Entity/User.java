package com.example.myblooddonor.Entity;

public class User {

    private int id ;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String city;
    private String bloodType;
    private int age;
    private String last_donation;
    private int count_donation;


    public User(int id, String firstName, String lastName, String email, String password, String phone, String city, String bloodType, int age, String last_donation, int count_donation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.city = city;
        this.bloodType = bloodType;
        this.age = age;
        this.last_donation = last_donation;
        this.count_donation = count_donation;
    }

    public User(String firstName, String lastName, String email, String password, String phone, String city, String bloodType, int age, String last_donation, int count_donation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.city = city;
        this.bloodType = bloodType;
        this.age = age;
        this.last_donation = last_donation;
        this.count_donation = count_donation;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLast_donation() {
        return last_donation;
    }

    public void setLast_donation(String last_donation) {
        this.last_donation = last_donation;
    }

    public int getCount_donation() {
        return count_donation;
    }

    public void setCount_donation(int count_donation) {
        this.count_donation = count_donation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", age=" + age +
                ", last_donation='" + last_donation + '\'' +
                ", count_donation=" + count_donation +
                '}';
    }
}
