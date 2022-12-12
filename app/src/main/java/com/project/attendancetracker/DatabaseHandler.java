package com.project.attendancetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="usersdb";
    private static final int DB_VERSION=1;
    // main table which stores the attendance info
    private static final String ATTENDANCE_TABLE="attendance";
    private static final String SUBJECTS_COL="Subjects";
    private static final String PRESENT_COL="PresentDays";
    private static final String ABSENTDAYS_COL="AbsentDays";
    int adays=0;
    int pdays=0;

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + ATTENDANCE_TABLE + " ("
                + SUBJECTS_COL + " TEXT, "
                + PRESENT_COL + " INTEGER,"
                + ABSENTDAYS_COL + " INTEGER)";
        db.execSQL(query);
    }
    public void addAttendanceData(String subject,int presentdays,int absentdays){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SUBJECTS_COL,subject);
        values.put(PRESENT_COL,presentdays);
        values.put(ABSENTDAYS_COL,absentdays);
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
            System.out.println("values updated");
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
        cv.put(ABSENTDAYS_COL,cl);
        try{
            db.update(ATTENDANCE_TABLE,cv,"Subjects=?",new String[]{subjectName});
            System.out.println("updated");
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
    public int getPresentDays(String subname){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=db.rawQuery("select PresentDays from attendance where subjects=?",new String[] {subname});
        while(cr.moveToNext()){
            pdays=cr.getInt(0);
        }
        return pdays;
    }
    public int getAbsentDays(String subname){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cr=db.rawQuery("select AbsentDays from attendance where subjects=?",new String[] {subname});
        while (cr.moveToNext()){
            adays=cr.getInt(0);
        }
        return adays;
    }
    public ArrayList<String> getAllSubjects(){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<String> getSubjects=new ArrayList();
        String query="select Subjects from attendance";
        Cursor cr=db.rawQuery(query,null);
        if(cr.moveToFirst()){
            do{
                getSubjects.add(cr.getString(0));
            }while (cr.moveToNext());
        }

        return getSubjects;
    }
}
