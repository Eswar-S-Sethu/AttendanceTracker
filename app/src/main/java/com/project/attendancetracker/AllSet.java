package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class AllSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_set);
        ArrayList<String> subslist=(ArrayList<String>) getIntent().getSerializableExtra("subjectary");

    }
}