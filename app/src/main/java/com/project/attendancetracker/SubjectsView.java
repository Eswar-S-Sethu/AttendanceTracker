package com.project.attendancetracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubjectsView extends AppCompatActivity {

    Button smryBtn,deleteAllbtn;
    DatabaseHandler dbh=new DatabaseHandler(this);
    UserDataHandler usr=new UserDataHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_view);

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);

        smryBtn=(Button) findViewById(R.id.button);
        deleteAllbtn=(Button) findViewById(R.id.button2);
        smryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSummary();
            }
        });
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
    public void showSummary(){
        Intent intent=new Intent(this,ShowSummaryActivity.class);
        startActivity(intent);
    }
    public void cleanHouse(){
        dbh.deleteAllAttendanceContent();
        usr.deleteAllUserData();
    }
    public void fromTheBeginning(){
        Intent intent=new Intent(this,ControlClass.class);
        startActivity(intent);
    }
}