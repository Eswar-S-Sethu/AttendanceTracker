package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectsView extends AppCompatActivity {

    Button smryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_view);
        smryBtn=(Button) findViewById(R.id.button);
        smryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSummary();
            }
        });
    }
    public void showSummary(){
        Intent intent=new Intent(this,ShowSummaryActivity.class);
        startActivity(intent);
    }
}