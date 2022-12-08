package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AddSubjects extends AppCompatActivity {

    TextView subjectsView,subjectsCount;
    Button addNewBtn,clearAllBtn,nextActBtn;
    static ArrayList<String> subjectarray=new ArrayList<String>();
    String subsCount;
    int arraylistCapacity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        subsCount=getIntent().getStringExtra("subjectsCount");
        arraylistCapacity=Integer.parseInt(subsCount);
        subjectarray.ensureCapacity(arraylistCapacity);

        subjectsView=(TextView) findViewById(R.id.subjectsSpace);
        subjectsCount=(TextView) findViewById(R.id.subjectsCountSpace);
        addNewBtn=(Button) findViewById(R.id.addNewSub);
        clearAllBtn=(Button) findViewById(R.id.allClearBtn);
        nextActBtn=(Button) findViewById(R.id.nextBtn);

        subjectsView.setText("Subjects:");
        subjectsCount.setText("Subjects added:");

        clearAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectsView.setText("Subjects:");
                subjectsCount.setText("Subjects added:");
                subjectarray.clear();
            }
        });

    }
}