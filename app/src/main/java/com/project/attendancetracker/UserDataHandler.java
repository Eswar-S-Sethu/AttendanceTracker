package com.project.attendancetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDataHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="userdata";
    private static final int DB_VERSION=1;
    private static final String USER_TABLE="user";
    private static final String USER_NAME="username";
    private static final String SUBJECTS="subjects";
    private static final String FIRST_TIME="firsttime";

    public UserDataHandler(Context context){
        super(context,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query="CREATE TABLE "+USER_TABLE+" ("
                +USER_NAME+" TEXT,"
                +SUBJECTS+" INTEGER,"
                +FIRST_TIME+" INTEGER)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldV,int newV){
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
    }
    public void addUserData(String username,int noofsub,int firstT){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(USER_NAME,username);
        values.put(SUBJECTS,noofsub);
        values.put(FIRST_TIME,firstT);
        try{
            db.insert(USER_TABLE,null,values);
            System.out.println("values inserted");
        }
        catch (Exception e){
            System.out.println("error");
        }
        db.close();
    }
    public void deleteAllUserData(){
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            db.delete(USER_TABLE,null,null);
            System.out.println("user data deleted");
        }
        catch (Exception e){
            System.out.println("Error");
        }
        db.close();
    }
    public int getFirstTime(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select firsttime from "+USER_TABLE;
        Cursor cr=db.rawQuery(query,null);
        int val=0;
        while (cr.moveToNext()){
            val=cr.getInt(0);
        }
        return val;
    }
    public String getUserName(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select username from "+USER_TABLE;
        Cursor cr=db.rawQuery(query,null);
        String uname="";
        while(cr.moveToNext()){
            uname=cr.getString(0);
        }
        return uname;
    }
    public int getSubjectCount(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select subjects from "+USER_TABLE;
        Cursor cr=db.rawQuery(query,null);
        int subCount=0;
        while (cr.moveToNext()){
            subCount=cr.getInt(0);
        }
        return subCount;
    }
}
