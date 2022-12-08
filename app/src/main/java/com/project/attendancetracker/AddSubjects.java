package com.project.attendancetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddSubjects extends AppCompatActivity {

    TextView subjectsView,subjectsCount;
    Button addNewBtn,clearAllBtn,nextActBtn;
    static ArrayList<String> subjectarray=new ArrayList<String>();
    String subsCount;
    int arraylistCapacity=0;
    int subjectsAdded=0;
    String newSubject;

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
        subjectsCount.setText("Subjects added:"+subjectsAdded);

        clearAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectsAdded=0;
                subjectsView.setText("Subjects:");
                subjectsCount.setText("Subjects added:"+subjectsAdded);
                subjectarray.clear();
                Toast.makeText(getApplicationContext(),"Cleared",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void showCustomAlertDialog(View view){  // WRONG CODE caused the issue. Work on this tmrw
        if(subjectsAdded>arraylistCapacity){
            Toast.makeText(getApplicationContext(),"You have added all subjects",Toast.LENGTH_LONG).show();
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Add subject");

        final View customLayout=getLayoutInflater().inflate(R.layout.custom_dialogbox,null);
        builder.setView(customLayout);

        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText=customLayout.findViewById(R.id.subjectName);
                addDataToArrayList(editText.getText().toString());
            }
        });
    }
    private void addDataToArrayList(String data){
        subjectarray.add(data);
        subjectsAdded=subjectsAdded+1;
        newSubject=data;
        updateValues();
        Toast.makeText(getApplicationContext(),data+" has been added",Toast.LENGTH_LONG).show();
    }
    public void updateValues(){
        subjectsCount.setText("Subjects added:"+subjectsAdded);
        subjectsView.setText("Subjects:"+newSubject+"\n");
    }
}