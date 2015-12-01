package com.example.localadmin.g11_ajh265_kex496.database;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Define the version and database file name
    private static final String DB_NAME = "example.db";
    private static final int DB_VERSION = 1;

    // Use a static class to defined the data structure
    // This will come in very handy if you using Agile
    // As your development model
    private static class UserTable {
        private static final String NAME = "user";
        private static final String COL_ID = "id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    private SQLiteDatabase db;

    // Constructor to simplify Business logic access to the repository
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        // Android will look for the database defined by DB_NAME
        // And if not found will invoke your onCreate method
        this.db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)",
                UserTable.NAME, UserTable.COL_ID,
                UserTable.COL_USERNAME, UserTable.COL_PASSWORD));

    }


    public void insert( GridWrapper gs ){
        int[][]grid = gs.getGrid();
        try {
            // Serialize the input array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(grid);
            byte[] blob = stream.toByteArray();

            // Set up database for insert
            String sql = "INSERT INTO " + this.getDatabaseName() + " VAlUES(KEY,TEXT);";
            SQLiteDatabase db = getWritableDatabase();
            SQLiteStatement statement = db.compileStatement(sql);

            // Insert into database
            db.beginTransaction();
            statement.clearBindings();
            statement.bindLong(1, gs.getTimeStamp());
            statement.bindBlob(2, blob);
            statement.execute();
            db.setTransactionSuccessful();
            db.endTransaction();

        } catch( IOException e ){
            Log.e("HELP", "IO EXCEPTION WRITING TO DB");
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // Later when you change the DB_VERSION
        // This code will be invoked to bring your database
        // Upto the correct specification




    }
}