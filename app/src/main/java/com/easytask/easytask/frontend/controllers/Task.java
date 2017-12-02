package com.easytask.easytask.frontend.controllers;


/**
 * Created by Silas on 12-10-2017.
 */

public class Task {

    protected String card_subject;
    protected String card_description;
    protected String taskID;
    protected int taskReward;


    public Task(String card_subject, String card_description, String taskID, int taskReward) {
        this.card_subject = card_subject;
        this.card_description = card_description;
        this.taskID = taskID;
        this.taskReward = taskReward;


    }

    public String getCard_subject() {
        return card_subject;
    }
    public void setCard_subject(String subject) {this.card_subject = card_subject;}

    public String getCard_description() {
        return card_description;
    }
    public void setCard_description(String card_description) {this.card_description = card_description;}

    public String getTaskID() {
        return taskID;
    }
    public void setTaskID(String taskID) {this.taskID = taskID;}

    public int getTaskReward() {
        return taskReward;
    }
    public void setTaskReward(int taskReward) {this.taskReward = taskReward;}



}
