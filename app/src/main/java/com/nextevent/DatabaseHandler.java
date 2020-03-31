package com.nextevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nextevent.JavaBeans.Event;

import java.util.ArrayList;

/**
 * Handles Database queries for the app.
 *
 * @author Abel Anderson
 * @version 1.2
 * @since 28/03/2020
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //Data Information
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NextEvent";

    //Events Table
    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_RANK = "rank";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_START = "start";
    private static final String COLUMN_END = "event_end";
    private static final String COLUMN_LONGITUDE = "longitude";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_IS_SAVED = "is_saved";

    //Labels Table
    private static final String TABLE_LABELS = "labels";
    private static final String COLUMN_EVENT_ID = "event_id";
    private static final String COLUMN_NAME = "name";

    //Create Queries

    //Events Table
    private static final String CREATE_EVENTS_TABLE = "CREATE TABLE " +
            TABLE_EVENTS + " (" +
            COLUMN_ID + " TEXT PRIMARY KEY," +
            COLUMN_TITLE + " TEXT," +
            COLUMN_DESCRIPTION + " TEXT," +
            COLUMN_CATEGORY + " TEXT," +
            COLUMN_RANK + " INTEGER," +
            COLUMN_DURATION + " INTEGER," +
            COLUMN_START + " TEXT," +
            COLUMN_END + " TEXT," +
            COLUMN_LONGITUDE + " REAL," +
            COLUMN_LATITUDE + " REAL," +
            COLUMN_COUNTRY + " TEXT," +
            COLUMN_IMAGE + " TEXT," +
            COLUMN_IS_SAVED + " INTEGER)";

    //Labels Table
    private static final String CREATE_LABELS_TABLE = "CREATE TABLE " +
            TABLE_LABELS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_EVENT_ID + " TEXT," +
            COLUMN_NAME + " TEXT," +
            " FOREIGN KEY (" + COLUMN_EVENT_ID + ") REFERENCES " + TABLE_EVENTS + "(" + COLUMN_ID + "))";

    /**
     * Constructor for the Database. This will access the database, or create a new database if it doesn't exist already.
     *
     * @param context The current context.
     */
    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create the SQLite Database. This will be automatically be called if the database does not already exist
     *
     * @param SQLiteDatabase The connection to the app's SQLiteDatabase.
     */
    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {
        SQLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        SQLiteDatabase.execSQL(CREATE_LABELS_TABLE);
    }

    /**
     * Handles Database upgrade. This will run update queries when the app updates the database.
     *
     * @param sqLiteDatabase The connection to the app's SQLiteDatabase.
     * @param oldVersion     The old database version.
     * @param newVersion     The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


    /**
     * Add a new event to the Database.
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        //Get the Writable Database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create the Content Values
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, event.getId());
        values.put(COLUMN_TITLE, event.getTitle());
        values.put(COLUMN_DESCRIPTION, event.getDescription());
        values.put(COLUMN_CATEGORY, event.getCategory());
        values.put(COLUMN_RANK, event.getRank());
        values.put(COLUMN_DURATION, event.getDuration());
        values.put(COLUMN_START, event.getStart());
        values.put(COLUMN_END, event.getEnd());
        values.put(COLUMN_LONGITUDE, event.getLang());
        values.put(COLUMN_LATITUDE, event.getLat());
        values.put(COLUMN_COUNTRY, event.getCountry());
        values.put(COLUMN_IMAGE, event.getImage());

        //Insert the Data & close the Database
        db.insert(TABLE_EVENTS, null, values);

        //Create Labels for Event
        for ( String label : event.getLabels() ) {
            ContentValues labelValues = new ContentValues();
            labelValues.put(COLUMN_EVENT_ID, event.getId());
            labelValues.put(COLUMN_NAME, label);
            db.insert(TABLE_LABELS, null, labelValues);
        }
        db.close();
    }

    // RETRIEVE

    /**
     * Grab an event from the database.
     *
     * @param id The id of the object to get.
     * @return The event you grabbed.
     */
    public Event getEvent(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Event event = null;
        Cursor cursor = db.query(TABLE_EVENTS, new String[]{
                COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_CATEGORY, COLUMN_RANK, COLUMN_DURATION, COLUMN_START, COLUMN_END, COLUMN_LONGITUDE, COLUMN_LATITUDE, COLUMN_COUNTRY, COLUMN_IMAGE
        }, COLUMN_ID + "= ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            event = new Event(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    getEventLabels(id),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    new double[]{cursor.getDouble(8), cursor.getDouble(9)},
                    cursor.getString(10),
                    cursor.getString(11)
            );
        }

        db.close();
        return event;
    }

    /**
     * Grab all the events from the table.
     *
     * @return An ArrayList of Events.
     */
    public ArrayList<Event> getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENTS,
                null);
        ArrayList<Event> equipment = new ArrayList<>();
        while (cursor.moveToNext()) {
            equipment.add(new Event(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    new String[]{"Ghaitgh"},
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    new double[]{cursor.getDouble(8), cursor.getDouble(9)},
                    cursor.getString(8),
                    cursor.getString(9)
            ));
        }
        db.close();
        return equipment;
    }

    /**
     * Get all the Labels for a Specified Event.
     * @param eventId The Event to get Labels for.
     * @return An array of Labels
     */
    private String[] getEventLabels(String eventId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LABELS +
                " WHERE " + COLUMN_EVENT_ID + " = " + eventId, null);

        ArrayList<String> labels = new ArrayList<>();

        while (cursor.moveToNext()) {
            labels.add(cursor.getString(2));
        }

        return (String[]) labels.toArray();
    }

    // UPDATE

    /**
     * Update an event on the table.
     *
     * @param event The event you are updating.
     * @return The verification status from the table.
     */
    public int updateEvent(Event event) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, event.getTitle());
        values.put(COLUMN_DESCRIPTION, event.getDescription());
        values.put(COLUMN_CATEGORY, event.getCategory());
        values.put(COLUMN_RANK, event.getRank());
        values.put(COLUMN_DURATION, event.getDuration());
        values.put(COLUMN_START, event.getStart());
        values.put(COLUMN_END, event.getEnd());
        values.put(COLUMN_LONGITUDE, event.getLang());
        values.put(COLUMN_LATITUDE, event.getLat());
        values.put(COLUMN_COUNTRY, event.getCountry());
        values.put(COLUMN_IMAGE, event.getImage());
        return db.update(TABLE_EVENTS, values, COLUMN_ID + " =?", new String[]{String.valueOf(event.getId())});
    }


    /**
     * Delete an event from the table.
     *
     * @param id The event to be deleted.
     */
    public void deleteEvent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, COLUMN_ID + " = ?", new String[]{id});
        db.close();
    }
}
