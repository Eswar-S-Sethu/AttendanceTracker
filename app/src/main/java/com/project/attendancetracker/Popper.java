package com.project.attendancetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.badge.BadgeUtils;

public class Popper extends AppCompatActivity {

    TextView subjectvw,percvw;
    Button presentbtn,absentbtn;
    DatabaseHandler dbh=new DatabaseHandler(this);
    String subject,pernum;
    double pres=0,abs=0,percent=0,att=0,abd=0;
    int rd=0,initial_percent=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popper);

        subjectvw=(TextView) findViewById(R.id.textView8);
        percvw=(TextView) findViewById(R.id.textView4);
        presentbtn=(Button) findViewById(R.id.button6);
        absentbtn=(Button) findViewById(R.id.button7);

        //pernum=getIntent().getStringExtra("percentage");
        //initial_percent=Integer.parseInt(pernum);

        subject=getIntent().getStringExtra("selected");
        System.out.println(subject);

        subjectvw.setText(subject);
        percvw.setText(initial_percent+" %");

        presentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                att=dbh.getPresentDays(subject);
                abd=dbh.getAbsentDays(subject);
                System.out.println("before incrementing:"+att+" "+abd);
                att=att+1;
                abd=abd+0;
                System.out.println("after incrementing:"+att+" "+abd);
                dbh.updateValues(subject, (int) att, (int) abd);
                presentbtn.setClickable(false);
                absentbtn.setClickable(false);
                pres=dbh.getPresentDays(subject);
                abs=dbh.getAbsentDays(subject);
                System.out.println(pres);
                System.out.println(abs);
                percent=pres/(pres+abs)*100;
                rd=(int) percent;
                percvw.setText(rd+" %");
                Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();
            }
        });
        absentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abd=dbh.getAbsentDays(subject);
                att=dbh.getPresentDays(subject);
                System.out.println("before incrementing:"+abd+" "+att);
                abd=abd+1;
                att=att+0;
                System.out.println("after incrementing:"+abd+" "+att);
                dbh.updateValues(subject, (int) att, (int) abd);
                presentbtn.setClickable(false);
                absentbtn.setClickable(false);
                pres=dbh.getPresentDays(subject);
                abs=dbh.getAbsentDays(subject);
                System.out.println(pres);
                System.out.println(abs);
                percent=pres/(pres+abs)*100;
                rd=(int) percent;
                System.out.println(percent);
                percvw.setText(rd+" %");
                Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
