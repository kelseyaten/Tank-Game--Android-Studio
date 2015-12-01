package com.example.localadmin.g11_ajh265_kex496.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.androidannotations.annotations.Background;


/**
 * Created by localadmin on 11/30/15.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "myDatabase.db";
    public static String DB_TABLE_NAME = "gameStates";
    public static String DB_COLUMN_TIMESTAMP = "timestamp";
    public static String DB_COLUMN_GRID = "grids";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        DATABASE_NAME = name;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table gameStates" + "(timestamp INTEGER PRIMARY KEY, grids BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }



}
