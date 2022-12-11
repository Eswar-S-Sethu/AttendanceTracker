package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ShowSummaryActivity extends AppCompatActivity {

    DatabaseHandler dbh=new DatabaseHandler(this);
    UserDataHandler usr=new UserDataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_summary);
    }
}