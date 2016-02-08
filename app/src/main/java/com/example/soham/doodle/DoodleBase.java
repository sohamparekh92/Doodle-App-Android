package com.example.soham.doodle;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Soham on 2/7/2016.
 */
public class DoodleBase extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "doodles.db";
    private static final String TABLE_NAME = "doodles_table";
    private static final int DOODLE_ID = 1;
    private static final String COLUMN_DOODLE_ID = "id";
    private static final String COLUMN_DOODLE_NAME = "doodle_name";
    private static final String COLUMN_DOODLE_COMMENT = "doodle_comment";


    public DoodleBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE '"+TABLE_NAME+"' ("
                +COLUMN_DOODLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_DOODLE_NAME+" TEXT, "
                +COLUMN_DOODLE_COMMENT+" TEXT );";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF EXISTS"+DB_NAME);
        onCreate(db);

    }

    public void addDoodle(DoodleManager doodle){

        String query = "INSERT INTO "+TABLE_NAME+"('"+COLUMN_DOODLE_NAME+"', '"+COLUMN_DOODLE_COMMENT+"')"
                +" VALUES " +
                "('"+doodle.getName()+"', '"
                +doodle.getComment()+"');";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
    public void deleteDoodle(DoodleManager doodle){

        String query = "DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_DOODLE_NAME+"= '"+doodle.getName()+"' ;" ;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
}
