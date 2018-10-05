package com.example.shubham.storageandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHandler {

    MyHelper myHelper;

    public MyHandler(Context context) {
        myHelper = new MyHelper(context);
    }

    long addPerson(Person person) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myHelper.KEY_NAME, person.get_name());
        contentValues.put(myHelper.KEY_AGE, person.get_age());
        contentValues.put(myHelper.KEY_ADDRESS, person.get_address());
        long id = db.insert(myHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    Person getPerson(String name) {
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query(myHelper.TABLE_NAME, new String[]{myHelper.KEY_ID, myHelper.KEY_NAME, myHelper.KEY_AGE, myHelper.KEY_ADDRESS}, myHelper.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Person person = new Person(Integer.parseInt(String.valueOf(Integer.parseInt(cursor.getString(0)))), cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3));
            return person;
        } else {
            return null;
        }
    }

    long deletePerson(String name) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        long id = db.delete(myHelper.TABLE_NAME, myHelper.KEY_NAME + "=?", new String[]{name});
        return id;
    }

    static class MyHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "personDatabase";
        private static final String TABLE_NAME = "personTable";
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_AGE = "age";
        private static final String KEY_ADDRESS = "address";


        public MyHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_PERSON_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " VARCHAR(30), " + KEY_AGE + " INTEGER, " + KEY_ADDRESS + " VARCHAR(100) " + ")";
            try {
                db.execSQL(CREATE_PERSON_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
