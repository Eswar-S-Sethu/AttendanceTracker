package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class subjectEnter extends AppCompatActivity {

    int subsnum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_enter);
        Intent intent=getIntent();
        subsnum= Integer.parseInt(intent.getStringExtra("numberOfSubs"));
    }
}