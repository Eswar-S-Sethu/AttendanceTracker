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
    DatabaseHandler dbh=new DatabaseHandler(this);
    private int firstTime=0;
    private int secs=2;
    private boolean checkTableEmptyOrNot;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.splashscreen);
        getSupportActionBar().hide();
        Utils.delay(secs, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                checkFirstTime();

            }
        });
    }


    public void checkFirstTime(){
        firstTime=usr.getFirstTime();
        checkTableEmptyOrNot=dbh.checkTable();
        if(firstTime==1 && checkTableEmptyOrNot==true){
            System.out.println("You are not a first time user");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        else if(firstTime==0){
            System.out.println("You are a first time user");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        else {
            Intent intent=new Intent(this,SubjectsView.class);
            startActivity(intent);
            this.finish();
        }
    }
}

