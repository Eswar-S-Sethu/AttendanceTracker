package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AllSet extends AppCompatActivity {

    Button gotItBtn;
    DatabaseHandler dbh=new DatabaseHandler(AllSet.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_set);

        gotItBtn=(Button) findViewById(R.id.button5);

        gotItBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr= dbh.getAllData();
                while (cr.moveToNext()){
                    System.out.println(cr.getString(0));
                    System.out.println(cr.getString(1));
                    System.out.println(cr.getString(2));
                }
                goToMainPage();
            }
        });
    }
    public void goToMainPage(){
        Intent intent=new Intent(this,SubjectsView.class);
        startActivity(intent);
    }
}