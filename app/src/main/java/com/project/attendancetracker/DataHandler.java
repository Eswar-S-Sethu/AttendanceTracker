package com.project.attendancetracker;

public class DataHandler {
    private String subjectName;
    private int presentDays;
    private int classDays;
    private int totalDays;
    private String userName;
    private int subCount;
    private boolean firstTime;

    // getter setter methods
    public String getSubjectName(){
        return subjectName;
    }
    public void setSubjectName(String subjectName){
        this.subjectName=subjectName;
    }
    public int getPresentDays(){
        return presentDays;
    }
    public void setPresentDays(int presentDays){
        this.presentDays=presentDays;
    }
    public int getClassDays(){
        return classDays;
    }
    public void setClassDays(int classDays){
        this.classDays=classDays;
    }
    public int getTotalDays(){
        return totalDays;
    }
    public void setTotalDays(int totalDays){
        this.totalDays=totalDays;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public int getSubCount(){
        return subCount;
    }
    public void setSubCount(int subCount){
        this.subCount=subCount;
    }
    public boolean getFirstTime(){
        return firstTime;
    }
    public void setFirstTime(boolean firstTime){
        this.firstTime=firstTime;
    }
    // constructor
    public DataHandler(String subjectName,int presentDays,int classDays){
        this.subjectName=subjectName;
        this.presentDays=presentDays;
        this.classDays=classDays;
    }
}
