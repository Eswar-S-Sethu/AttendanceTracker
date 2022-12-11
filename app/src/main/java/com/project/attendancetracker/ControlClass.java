package com.project.attendancetracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ControlClass extends AppCompatActivity {
    /**
     * This class will make sure that the app will open to the subject view
     * activity and if the user is using for the first time,then the details
     * activity must be displayed.*/
    UserDataHandler usr=new UserDataHandler(this);
    private int firstTime=0;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.splashscreen);
        checkFirstTime();
    }


    public void checkFirstTime(){
        firstTime=usr.getFirstTime();
        if(firstTime==1){
            System.out.println("You are not a first time user");
            Intent intent=new Intent(this,SubjectsView.class);
            startActivity(intent);
        }
        else if(firstTime==0){
            System.out.println("You are a first time user");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
