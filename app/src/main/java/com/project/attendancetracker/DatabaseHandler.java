package com.project.attendancetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="usernew";
    private static final int DB_VERSION=1;
    // main table which stores the attendance info
    private static final String ATTENDANCE_TABLE="attendance";
    private static final String SUBJECTS_COL="Subjects";
    private static final String PRESENT_COL="PresentDays";
    private static final String CLASSDAYS_COL="ClassDays";
    private static final String TOTALDAYS_COL="TotalDays";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + ATTENDANCE_TABLE + " ("
                + SUBJECTS_COL + " TEXT, "
                + PRESENT_COL + " INTEGER,"
                + CLASSDAYS_COL + " INTEGER,"
                + TOTALDAYS_COL + " INTEGER)";
        db.execSQL(query);
    }
    public void addAttendanceData(String subject,int presentdays,int classdays){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUBJECTS_COL,subject);
        values.put(PRESENT_COL,presentdays);
        values.put(CLASSDAYS_COL,classdays);
        try{
            db.insert(ATTENDANCE_TABLE,null,values);
            System.out.println("values inserted");
        }
        catch (Exception e){
            System.out.println("Error:Can't do it "+e);
        }
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldV,int newV){
        db.execSQL("DROP TABLE IF EXISTS "+ATTENDANCE_TABLE);
    }

    public void deleteAllAttendanceContent(){
        SQLiteDatabase db=this.getWritableDatabase();
        try{
            db.delete(ATTENDANCE_TABLE,null,null);
        }
        catch (Exception e){
            System.out.println("Error: Can't delete data");
        }
        db.close();
    }

    public void updateValues(String subjectName,int pr,int cl){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(PRESENT_COL,pr);
        cv.put(CLASSDAYS_COL,cl);
        try{
            db.update(ATTENDANCE_TABLE,cv,"Subjects=?",new String[]{subjectName});
        }
        catch (Exception e){
            System.out.println("Error: Can't update anything");
        }
        db.close();
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from "+ATTENDANCE_TABLE;
        Cursor res=db.rawQuery(query,null);
        return res;
    }
}
