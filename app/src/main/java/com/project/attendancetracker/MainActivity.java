package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nametxt,number_of_subjects;
    Button nextbtn;
    String subs,nm;
    int noofSubs=0;
    UserDataHandler usrdt=new UserDataHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nametxt=findViewById(R.id.name);
        getSupportActionBar().hide();

        number_of_subjects=findViewById(R.id.noofsubs);
        nextbtn=findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm=nametxt.getText().toString();
                subs=number_of_subjects.getText().toString();
                if(nm.length()==0 && subs.isEmpty()){
                    Toast.makeText(getApplicationContext(),"fill in all fields",Toast.LENGTH_SHORT).show();
                }
                else if(nm.length()!=0 && subs.isEmpty()){
                    Toast.makeText(getApplicationContext(),"fill in all fields",Toast.LENGTH_SHORT).show();
                }
                else if(nm.length()!=0 && Integer.parseInt(subs)==0){
                    Toast.makeText(getApplicationContext(),"stop playing  :|",Toast.LENGTH_SHORT).show();
                }
                else if(nm.length()==0){
                    Toast.makeText(getApplicationContext(),"fill in all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    noofSubs=Integer.parseInt(subs);
                    Toast.makeText(getApplicationContext(),"Add each subject",Toast.LENGTH_LONG).show();
                    usrdt.addUserData(nm,noofSubs,1);
                    showlistview();
                }
            }
        });
    }

    public void showlistview(){
        Intent intent=new Intent(this,AddSubjects.class);
        intent.putExtra("subjectsCount",subs);
        intent.putExtra("username",nm);
        startActivity(intent);
        this.finish();
    }
}