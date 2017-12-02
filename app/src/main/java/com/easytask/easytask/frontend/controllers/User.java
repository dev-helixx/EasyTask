package com.easytask.easytask.frontend.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silas on 02-11-2017.
 */

public class  User {

    private String email;
    private boolean taskCreator;
    private String name;
    private String address;
    private String zipCode;
    private String city;
    private String phonenumber;

//    private String date;
//    private int avatar;

    public User(String email, boolean taskCreator, String name, String address, String zipCode, String city, String phonenumber){
        this.email = email;
        this.taskCreator = taskCreator;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.phonenumber = phonenumber;
//        this.date = date;
//        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String name) {this.email = name;}

    public boolean getIsTaskCreator() {return taskCreator;}
    public void setIsTaskCreator(boolean taskCreator) {this.taskCreator = taskCreator;}

    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {this.address = address;}

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {this.zipCode = zipCode;}

    public String getCity() {
        return city;
    }
    public void setCity(String city) {this.city = city;}

    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {this.phonenumber = phonenumber;}




//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public int getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(int avatar) {
//        this.avatar = avatar;
//    }


}