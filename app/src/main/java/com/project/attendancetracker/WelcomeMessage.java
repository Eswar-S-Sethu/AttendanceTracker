package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeMessage extends AppCompatActivity {
    UserDataHandler usr=new UserDataHandler(this);

    public String setUserName(){
        String usname=usr.getUserName();
        System.out.println(usname);
        return usname;
    }
}
