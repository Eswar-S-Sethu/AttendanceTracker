package com.project.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowListItems extends AppCompatActivity {

    ListView lst;

    String[] subjects={"Subject_1","Subject_2","Subject_3","Subject_4","Subject_5"};
    String[] buttons_label={"Add"};

    int subjects_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_items);
        subjects_count = Integer.parseInt(getIntent().getStringExtra("numberOfSubs").toString());
        //ArrayList<Integer> ary=new ArrayList<>(subjects_count);

        ListItemsAssignerClass adapter = new ListItemsAssignerClass(this, subjects, buttons_label);
        lst = (ListView) findViewById(R.id.listView);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
                } else if (i == 1) {
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
                } else if (i == 2) {
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
                } else if (i == 3) {
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
                } else if (i == 4) {
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
                } else if (i == 5) {
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}