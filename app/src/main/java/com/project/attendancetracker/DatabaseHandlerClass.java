package com.project.attendancetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DatabaseHandlerClass extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="User.db";
    public static final String TABLE_NAME="user_data";
    public static final String COL_1="subjects";
    public static final String COL_2="attended_days";
    public static final String COL_3="class days";

    public DatabaseHandlerClass(Context context){
        super(context,DATABASE_NAME,null,1);
    }


}
