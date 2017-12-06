package com.easytask.easytask.frontend.controllers;


/**
 * Created by Silas on 12-10-2017.
 */

public class Task {

    private String card_subject;
    private String card_description;
    private String taskID;
    private String creatorID;
    private String taskReward;


    public Task(String card_subject, String card_description, String taskID, String taskReward, String creatorID) {
        this.card_subject = card_subject;
        this.card_description = card_description;
        this.taskID = taskID;
        this.taskReward = taskReward;
        this.creatorID = creatorID;


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

    public String getTaskReward() {
        return taskReward;
    }
    public void setTaskReward(String taskReward) {this.taskReward = taskReward;}

    public String getCreatorID() {
        return creatorID;
    }
    public void setCreatorID(String creatorID) {this.creatorID = creatorID;}



}
