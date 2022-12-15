package com.project.attendancetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
    String subsCount,uname;
    int arraylistCapacity=0;
    int subjectsAdded=0;
    String newSubject;
    DatabaseHandler dbh=new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        getSupportActionBar().hide();

        dbh.deleteAllAttendanceContent();

        subsCount=getIntent().getStringExtra("subjectsCount");
        uname=getIntent().getStringExtra("username");
        arraylistCapacity=Integer.parseInt(subsCount);

        subjectarray.ensureCapacity(arraylistCapacity);
        System.out.println(arraylistCapacity);
        subjectsView=(TextView) findViewById(R.id.subjectsSpace);
        subjectsCount=(TextView) findViewById(R.id.subjectsCountSpace);
        addNewBtn=(Button) findViewById(R.id.addNewSub);
        clearAllBtn=(Button) findViewById(R.id.allClearBtn);
        nextActBtn=(Button) findViewById(R.id.nextBtn);

        subjectsView.setMovementMethod(new ScrollingMovementMethod());

        subjectsView.setText("Subjects:");
        subjectsCount.setText("Subjects added:"+subjectsAdded+"/"+arraylistCapacity);

        clearAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectsAdded=0;
                subjectsView.setText("Subjects:");
                subjectsCount.setText("Subjects added:"+subjectsAdded+"/"+arraylistCapacity);
                subjectarray.clear();
                dbh.deleteAllAttendanceContent();
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
                if(subjectarray.isEmpty()){
                    Toast.makeText(getApplicationContext(),"type in all the subjects",Toast.LENGTH_SHORT).show();
                }
                else {
                    allInfoSavedActivity();
                }
            }
        });
    }
    public void showCustomAlertDialog(){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        View mview=getLayoutInflater().inflate(R.layout.custom_dialogbox,null);
        EditText txt_input=(EditText) mview.findViewById(R.id.subjectName);
        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {// make decisions
                addDataToArrayList(txt_input.getText().toString());
                String addtmp=txt_input.getText().toString();
                System.out.println(addtmp);
                dbh.addAttendanceData(addtmp,0,0);
            }
        });
        alert.setView(mview);
        AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }
    private void addDataToArrayList(String data){
        subjectarray.add(data);
        subjectsAdded=subjectsAdded+1;
        newSubject=data;
        updateValues();
    }
    public void updateValues(){
        System.out.println(subjectsAdded);
        subjectsCount.setText("Subjects added:"+subjectsAdded+"/"+arraylistCapacity);
        subjectsView.append(newSubject+"\n");
    }
    public void allInfoSavedActivity(){
        Intent intent=new Intent(this,AllSet.class);
        startActivity(intent);
        this.finish();
    }
}