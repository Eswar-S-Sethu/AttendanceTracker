package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WelcomeMessage extends AppCompatActivity {
    Date currentDate=new Date();

    SimpleDateFormat dateFormat=new SimpleDateFormat("kk");
    String timeinhrs= dateFormat.format(currentDate);
    int timenow=Integer.parseInt(timeinhrs);

    public String getGreeting(){
        String greeting="";
        if(timenow>00 && timenow<12){
            greeting="Good Morning";
        }
        else if(timenow>12 && timenow<=15){
            greeting="Good Afternoon";
        }
        else if(timenow>15){
            greeting="Good Evening";
        }
        return greeting;
    }
}
