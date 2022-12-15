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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SubjectsView extends AppCompatActivity {

    DatabaseHandler dbh=new DatabaseHandler(this);
    UserDataHandler usr=new UserDataHandler(this);
    TextView nametxt,greetingTxt;
    WelcomeMessage wm=new WelcomeMessage();
    ListView lst;
    ArrayList<String> subjectsList=new ArrayList<String>();
    String getusrnm,usrnmfix;
    String selectedItem;
    int capacity=0,Intperct;
    double pday=0,aday=0,perct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_view);
        lst=(ListView) findViewById(R.id.subjectsListVW);
        nametxt=(TextView) findViewById(R.id.textView5);
        greetingTxt=(TextView) findViewById(R.id.textView2);
        greetingTxt.setText(wm.getGreeting());
        usrnmfix=usr.getUserName().toLowerCase();
        getusrnm=usr.getUserName().substring(0,1).toUpperCase()+usrnmfix.substring(1);
        nametxt.setText(getusrnm);
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
                goToDetails();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the menu
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        int id=item.getItemId();
        switch (id){
            case R.id.deleteAllItem:
                dialog.setMessage("This will delete all data including the userdata saved in this device.The app will restart");
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
                break;
            case R.id.abt:
                Intent intent=new Intent(this,AboutUs.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void cleanHouse(){
        dbh.deleteAllAttendanceContent();
        usr.deleteAllUserData();
    }
    public void fromTheBeginning(){
        Intent intent=new Intent(this,ControlClass.class);
        startActivity(intent);
        this.finish();
    }
    public void goToDetails(){
        Intent intent=new Intent(this,Popper.class);
        intent.putExtra("selected",selectedItem);
        startActivity(intent);
    }
}