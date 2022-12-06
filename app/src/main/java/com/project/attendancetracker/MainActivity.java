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
    int subs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nametxt=findViewById(R.id.name);
        number_of_subjects=findViewById(R.id.noofsubs);
        nextbtn=findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Type in all the subjects",Toast.LENGTH_LONG).show();
                subs=Integer.parseInt(number_of_subjects.getText().toString());
                showlistview();
            }
        });
    }

    public void showlistview(){
        Intent intent=new Intent(getApplicationContext(),subjectEnter.class);
        intent.putExtra("numberOfSubs",subs);
        startActivity(intent);
    }
}