package com.nextevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nextevent.JavaBeans.Event;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NextEvent";

    private static final String TABLE_EVENTS = "events";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_IS_SAVED = "is_saved";
    private static final String COLUMN_IS_REGISTERED = "is_registered";

    private static final String CREATE_EVENTS_TABLE = "CREATE TABLE " +
            TABLE_EVENTS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," +
            COLUMN_IMAGE + " TEXT," + COLUMN_DATE + " TEXT," +
            COLUMN_IS_SAVED + " INTEGER," + COLUMN_IS_REGISTERED + "INTEGER)";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // CREATE
//    public void addEvent(Event event) {
//        //Get the Writable Database
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        //Create the Content Values
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME, event.getTitle());
//        values.put(COLUMN_DESCRIPTION, event.getDescription());
//        values.put(COLUMN_IMAGE, event.getImage());
//        values.put(COLUMN_DATE, event.getDate());
//        values.put(COLUMN_IS_SAVED, event.isSaved());
//        values.put(COLUMN_IS_REGISTERED, event.isRegistered());
//
//        //Insert the Data & close the Database
//        db.insert(TABLE_EVENTS, null, values);
//        db.close();
//    }


    // RETRIEVE
//    public Event getEvent(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Event event = null;
//        Cursor cursor = db.query(TABLE_EVENTS, new String[]{
//                COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_IMAGE, COLUMN_DATE, COLUMN_IS_SAVED, COLUMN_IS_REGISTERED
//        }, COLUMN_ID + "= ?", new String[]{String.valueOf(id)}, null, null, null);
//
//        if(cursor.moveToFirst()) {
//            event = new Event(
//                    cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    cursor.getString(4),
//                    cursor.getInt(5),
//                    cursor.getInt(6)
//            );
//        }
//
//        db.close();
//        return event;
//    }

//    public ArrayList<Event> getAllEvents(){
//        SQLiteDatabase db  = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENTS ,
//                null);
//        ArrayList<Event> equipment = new ArrayList<>();
//        while(cursor.moveToNext()) {
//            equipment.add(new Event(
//                    cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    cursor.getString(4),
//                    cursor.getInt(5),
//                    cursor.getInt(6)));
//        }
//        db.close();
//        return equipment;
//    }


    // UPDATE



    // DELETE
//    public void deleteEvent(int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_EVENTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
//        db.close();
//    }
}
