package com.easytask.easytask.frontend.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silas on 02-11-2017.
 */

public class  User {

    private String email;
    private boolean taskCreator;
//    private String date;
//    private int avatar;

    public User(String email, boolean taskCreator){
        this.email = email;
        this.taskCreator = taskCreator;
//        this.date = date;
//        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String name) {this.email = name;}

    public boolean getIsTaskCreator() {return taskCreator;}
    public void setIsTaskCreator(boolean taskCreator) {this.taskCreator = taskCreator;}

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