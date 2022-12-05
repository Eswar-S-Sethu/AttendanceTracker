package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nametxt,number_of_subjects;
    Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nametxt=findViewById(R.id.name);
        number_of_subjects=findViewById(R.id.noofsubs);
        nextbtn=findViewById(R.id.nextbtn);
    }
}