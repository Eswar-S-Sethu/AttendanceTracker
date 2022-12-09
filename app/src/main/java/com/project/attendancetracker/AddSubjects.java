package com.project.attendancetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
        System.out.println(arraylistCapacity);

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
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subjectsAdded<arraylistCapacity){
                    showCustomAlertDialog();
                }
                else {
                    Toast.makeText(getApplicationContext(),"You have added all subjects",Toast.LENGTH_LONG).show();
                    for(String name:subjectarray)
                        System.out.println(name);
                }
            }
        });
        nextActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allInfoSavedActivity();
            }
        });
    }
    public void showCustomAlertDialog(){
        final AlertDialog.Builder alert=new AlertDialog.Builder(AddSubjects.this);
        View mview=getLayoutInflater().inflate(R.layout.custom_dialogbox,null);
        final EditText txt_input=(EditText) mview.findViewById(R.id.subjectName);
        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addDataToArrayList(txt_input.getText().toString());
            }
        });
        alert.setView(mview);
        final AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }
    private void addDataToArrayList(String data){
        subjectarray.add(data);
        subjectsAdded=subjectsAdded+1;
        newSubject=data;
        updateValues();
        Toast.makeText(getApplicationContext(),data+" has been added",Toast.LENGTH_LONG).show();
    }
    public void updateValues(){
        System.out.println(subjectsAdded);
        subjectsCount.setText("Subjects added:"+subjectsAdded);
        subjectsView.setText("Subjects:"+newSubject+"\n");
    }
    public void allInfoSavedActivity(){
        Intent intent=new Intent(this,AllSet.class);
        intent.putExtra("subjectary",subjectarray);
        startActivity(intent);
    }
}