package com.project.attendancetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.snackbar.Snackbar;

public class Popper extends AppCompatActivity {

    TextView subjectvw,percvw;
    Button presentbtn,absentbtn;
    DatabaseHandler dbh=new DatabaseHandler(this);
    String subject;
    double pres=0,abs=0,percent=0,att=0,abd=0,presentDys=0,absentDys=0,initial_percent=0;
    int rd=0,roundOf=0;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popper);

        subjectvw=(TextView) findViewById(R.id.textView8);
        percvw=(TextView) findViewById(R.id.textView4);
        presentbtn=(Button) findViewById(R.id.button6);
        absentbtn=(Button) findViewById(R.id.button7);
        constraintLayout=(ConstraintLayout) findViewById(R.id.popper_layout);


        subject=getIntent().getStringExtra("selected");
        System.out.println(subject);

        /**
         * Implementing the functionality to display the current percentage
         * which was not present before. */
        presentDys=dbh.getPresentDays(subject);
        absentDys= dbh.getAbsentDays(subject);
        initial_percent=presentDys/(presentDys+absentDys)*100;
        roundOf=(int) initial_percent;


        subjectvw.setText(subject);
        percvw.setText(roundOf+" %");

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
               Snackbar snackbar=Snackbar.make(constraintLayout,"Attendance added",Snackbar.LENGTH_LONG);
               snackbar.show();
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
                Snackbar snackbar=Snackbar.make(constraintLayout,"Attendance added",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

}
