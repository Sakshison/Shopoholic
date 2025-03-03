package com.example.shopoholic.activities;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "user.db";

    public dbhelper(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("create table registration( email varchar PRIMARY KEY,username text,phonenum varchar ,password text)");
        mydb.execSQL("create table admin( email varchar PRIMARY KEY,username text,phonenum varchar ,password text)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldversion, int newversion)
    {
        mydb.execSQL("Drop table if exists registration");
        mydb.execSQL("Drop table if exists admin");
        onCreate(mydb);
    }
    public Boolean insertreg(String username, String email,String phonenum, String password)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("phonenum", phonenum);
        values.put("password", password);
        long result = mydb.insert("registration", null,values);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public void insertadmin(String username, String email,String phonenum, String password)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("phonenum", phonenum);
        values.put("password", password);
        long result = mydb.insert("admin", null,values);
    }
    public Boolean checkid(String email)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from registration where email = ?",new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Cursor getpassword(String uemail)
    {
        Cursor res=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select * from registration where email ='" + uemail + "'", null);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    public Cursor getadmin_pass(String uemail)
    {
        Cursor res=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select * from admin where email ='" + uemail + "'", null);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return res;
    }
}
