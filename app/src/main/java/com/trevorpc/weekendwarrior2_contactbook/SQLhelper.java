package com.trevorpc.weekendwarrior2_contactbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.Telephony.Carriers.PASSWORD;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.ADDRESS;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.DATABASE_NAME;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.FIRST_NAME;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.LAST_NAME;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.PHONE;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.PRIMARY_KEY_EMAIL;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.SKYPE;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.TABLE_NAME;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.TAG;

public class SQLhelper extends SQLiteOpenHelper
{
    public SQLhelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME
                + "(" + PRIMARY_KEY_EMAIL + " TEXT PRIMARY KEY, "
                + FIRST_NAME + " TEXT, "
                + LAST_NAME + " TEXT, "
                + ADDRESS + " TEXT, "
                + SKYPE + " TEXT, "
                + PHONE + " TEXT)";

        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        onCreate(sqLiteDatabase);
    }

    public void insert (String email, String first, String last, String address, String skype, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRIMARY_KEY_EMAIL, email);
        contentValues.put(FIRST_NAME, first);
        contentValues.put(LAST_NAME, last);
        contentValues.put(ADDRESS, address);
        contentValues.put(SKYPE, skype);
        contentValues.put(PHONE, phone);


        db.insert(TABLE_NAME, null, contentValues);
    }


    public Cursor getAllContacts()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        return res;
    }

    public Cursor getContactsByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME  + " WHERE " + PRIMARY_KEY_EMAIL + " = \"" +  email + "\"", null );
        return res;
    }

    public Cursor getAllEmails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select " + PRIMARY_KEY_EMAIL + " from " + TABLE_NAME, null );
        return res;
    }
    public Cursor getContactsByFirstName(String first)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME  + " WHERE " + FIRST_NAME + " = \"" +  first + "\"", null );
        return res;
    }
    public boolean deleteContact( String email)
    {
        Log.d("TAG", "deleteContact: started");
        SQLiteDatabase db =this.getWritableDatabase();
        Log.d("TAG", "deleteContact: after db "+email);
        db.delete(TABLE_NAME , PRIMARY_KEY_EMAIL + " = \"" +  email + "\"",null);
        Log.d("TAG", "deleteContact: after raw");
        return true;

    }
}


