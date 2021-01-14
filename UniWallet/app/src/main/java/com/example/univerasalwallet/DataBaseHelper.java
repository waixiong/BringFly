package com.example.univerasalwallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, "UniWallet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Called the first time the database is accessed.
        // there must be code here inorder to create database we
        // named in the constructor super.

        // ceates users table
        String queryCreateUsersTable = "Create table users" +
            "(UID integer primary key autoincrement, " +
            "name text, " +
            "phone text, " +
            "email text, " +
            "pwd text)";
        db.execSQL(queryCreateUsersTable);

        String queryCreateWalletsTable = "Create table wallets" +
                "(WID integer primary key autoincrement, " +
                "walletName text," +
                "logo text)";
        db.execSQL(queryCreateWalletsTable);

        String queryCreateBanksTable = "Create table banks" +
                "(BID integer primary key autoincrement, " +
                "bankName text," +
                "logo text)";
        db.execSQL(queryCreateBanksTable);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String queryDropUsersTable = "drop table if exists users";
        db.execSQL(queryDropUsersTable);
    }

    // login
    public boolean login(String email, String pwd)
    {
        String query = "select email, pwd from users where email = ? and pwd = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email,pwd});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    // Get the userid after login
    public int getUid(String email)
    {
        String query = "Select uid from users where email = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if(cursor.getCount()>0)
            return cursor.getInt(0);
        else return 0;
    }

    public String getUserData(int uid)
    {
        String query = "Select * from users where uid = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(uid)});
        if(cursor.getCount() > 0) return cursor.getString(Integer.parseInt("name"));
        else return null;
    }

    // create new account (Insert)
    public boolean addUser(String name, String phone, String email, String pwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("email", email);
        cv.put("pwd", pwd);
        long insert = db.insert("users", null, cv);
        if(insert == -1) return false;
        else return true;
    }

    // Check if email exists;
    public boolean isExist(String email)
    {
        String query = "Select email from users where email = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if(cursor.getCount()>0) return true; //means email is not uinque and exists
        else return false;
    }
}






//    public String getUser()
//    {
//        String user ="";
//        String query = "Select email from users";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        if(cursor.getCount()!=0)
//        {
//            while(cursor.moveToNext())
//            {
//                user = " id " + cursor.getString(0) + "\n" +
//                        "name " + cursor.getString(1) + "\n" +
//                        "email " + cursor.getString(Integer.parseInt("email"));
//            }
//        }
//        return user;
//
//
//    }