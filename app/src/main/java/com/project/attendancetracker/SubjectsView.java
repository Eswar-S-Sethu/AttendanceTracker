package com.project.attendancetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SubjectsView extends AppCompatActivity {

    Button smryBtn,deleteAllbtn;
    DatabaseHandler dbh=new DatabaseHandler(this);
    UserDataHandler usr=new UserDataHandler(this);
    ListView lst;
    ArrayList<String> subjectsList=new ArrayList<String>();
    String selectedItem;
    int capacity=0,Intperct;
    double pday=0,aday=0,perct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_view);
        lst=(ListView) findViewById(R.id.subjectsListVW);
        capacity=usr.getSubjectCount();
        System.out.println(capacity);
        subjectsList.ensureCapacity(capacity);
        subjectsList=dbh.getAllSubjects();
        System.out.println(subjectsList);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,subjectsList);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem=(String) parent.getItemAtPosition(position);
                System.out.println(selectedItem);
                pday=dbh.getPresentDays(selectedItem);
                aday=dbh.getAbsentDays(selectedItem);
                perct=pday/(pday+aday)*100;
                Intperct=(int) perct;
                goToDetails();
            }
        });

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);

        deleteAllbtn=(Button) findViewById(R.id.button2);


        deleteAllbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("This will delete all data including the userdata saved in this device.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Go for it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cleanHouse();
                        Toast.makeText(getApplicationContext(),"All done", Toast.LENGTH_SHORT).show();
                        fromTheBeginning();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "Operation cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert=dialog.create();
                alert.setTitle("Delete data?");
                alert.show();
            }
        });
    }

    public void cleanHouse(){
        dbh.deleteAllAttendanceContent();
        usr.deleteAllUserData();
    }
    public void fromTheBeginning(){
        Intent intent=new Intent(this,ControlClass.class);
        startActivity(intent);
    }
    public void goToDetails(){
        Intent intent=new Intent(this,Popper.class);
        intent.putExtra("selected",selectedItem);
        intent.putExtra("percentage",perct);
        startActivity(intent);
    }
}