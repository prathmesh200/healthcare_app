package com.example.healthcare_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String dbname = "Login.db";

    public Database(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        /*
        String qryl="create table users(username text, email text,phoneno text,pasward text)";
        sqLiteDatabase.execSQL(qryl);
        */

        MyDB.execSQL("create Table card(username text,product text,price float,otype text)");

        MyDB.execSQL("create Table orderplace(username text, fullname text, address text, contactno text, pincode int,date text, time text, amount float, otype text)");


        MyDB.execSQL("create Table users(username TEXT primary key,fullname TEXT, passward TEXT,email TEXT,phoneno TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");

    }
   /* public void register(String username,String email,String passward, String phoneno){
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("passward",passward);
        cv.put("phoneno",phoneno);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username,String passward){
        int result=0;
        String arr[]=new String[2];
        arr[0]=username;
        arr[1]=passward;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where username=? and passward=?",arr);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }*/


    public Boolean insertData(String username, String fullname, String passward, String email, String phoneno) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("username", username);
        contentValues.put("passward", passward);
        contentValues.put("email", email);
        contentValues.put("phoneno", phoneno);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkusernamepassward(String username, String passward) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username=? and passward=?", new String[]{username, passward});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    // for card table


    public void addcard(String username, String prodcut, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", prodcut);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("card", null, cv);
        db.close();
    }

    public int checkcard(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from card where username = ? and product = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

    public void removecard(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("card", "username=? and otype=?", str);
        db.close();

    }

    public ArrayList getCardData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from card where username =? and otype =?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);

            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }


    //add order function
    public void addorder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();


    }

    //get order function

    public ArrayList getorderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c=db.rawQuery("select * from orderplace where username =?",str);
        if(c.moveToNext()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));

            }while(c.moveToNext());
        }
db.close();
        return arr;
    }

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date,String time){
        int result=0;
        String str[]=new String[6];
        str[0]=username;
        str[1]=fullname;
        str[2]=address;
        str[3]=contact;
        str[4]=date;
        str[5]=time;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from orderplace where username=? and fullname = ? and address = ? and contactno= ? and date = ? and time = ?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
}


