package com.project.attendancetracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ActivityViewModelLazyKt;

public class ListItemsAssignerClass extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] subjects;
    private final String[] buttons;

    public ListItemsAssignerClass(Activity context,String[] subjects,String[] buttons){
        super(context,R.layout.activity_subject_enter);
        this.context=context;
        this.subjects=subjects;
        this.buttons=buttons;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_subject_enter,null,true);

        EditText subject_edit=(EditText) rowView.findViewById(R.id.subnme);
        Button addsubbtn=(Button) rowView.findViewById(R.id.addbtn);

        subject_edit.setHint(subjects[position]);
        addsubbtn.setText(buttons[position]);

        return rowView;
    }
}
