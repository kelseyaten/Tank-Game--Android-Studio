package com.example.localadmin.g11_ajh265_kex496;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.localadmin.g11_ajh265_kex496.UI.GridAdapter;
import com.example.localadmin.g11_ajh265_kex496.database.DBHelper;
import com.example.localadmin.g11_ajh265_kex496.database.DataBaseHelper;
import com.example.localadmin.g11_ajh265_kex496.database.GridWrapperDatab;
import com.example.localadmin.g11_ajh265_kex496.database.ReplayStructure;
import com.example.localadmin.g11_ajh265_kex496.rest.Controller;
import com.example.localadmin.g11_ajh265_kex496.rest.UIUpdate;
import com.example.localadmin.g11_ajh265_kex496.sensor.ShakeListener;
import com.example.localadmin.g11_ajh265_kex496.sensor.ShakeListenerManager;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.Tank;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;


@EActivity
public class TankClientActivity extends AppCompatActivity implements ShakeListener, Observer{


    @Bean
    Controller myController;

    @Bean
    static ReplayStructure myReplayStructure;


    @Bean
    ShakeListenerManager sd;

    private static DBHelper myDb;

    public static DBHelper getHelper(){

        return myDb;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_client);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @AfterViews
    protected void afterViewInjection() {

        myController.implement( findViewById(R.id.gridView));
        sd = new ShakeListenerManager(getApplicationContext());
        sd.setListener(this);

        Tank.getMyLogicTank().addObserver(this);
        UIUpdate.getmyGridWrapperDatab().addObserver(this );
        myDb = new DBHelper(getApplicationContext(), "gameStates.db", null, 1);


    }


    @Click({R.id.buttonLeft, R.id.buttonRight, R.id.buttonUp, R.id.buttonDown, R.id.buttonFire})
    void onClick( View v ) {


        if( GridAdapter.islocated == false ){

            Intent myIntent = new Intent(getBaseContext(), ReplayActivity_.class);
            startActivity(myIntent);
        }


        myController.clicked(v);

    }



    @Override
    public void onShake() {

       myController.fire();

    }


    @Override
    public void update(Observable observable, Object data) {



        if( observable instanceof GridWrapperDatab){


           // if(    GridAdapter.islocated != false )
            //insertIntoDatabase(UIUpdate.getmyGridWrapperDatab().getMyWrapper() );

            if( GridAdapter.islocated != false ) {
                myReplayStructure.insert(UIUpdate.getmyGridWrapperDatab().getMyWrapper());
            }


        }else{

            Log.d("HIT", "help");
            long life = Tank.getMyLogicTank().getLife();
            TextView myView = (TextView) findViewById(R.id.life);
            if( life == 1 ) {

                myView.setText("♡♡♡");
            }
            if (life == 1) {

                myView.setText("♥♡♡");
            }
            if (life == 2) {

                myView.setText("♥♥♡");
            }
            if (life == 3) {

                myView.setText("♥♥♥");
            }


        }
    }


    @Background
    public void insertGrid( byte[] blob ){


        SQLiteDatabase db = myDb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( myDb.DB_COLUMN_TIMESTAMP, (String) null );
        cv.put(myDb.DB_COLUMN_GRID, blob);
        db.insert(myDb.DB_TABLE_NAME, null, cv);
        //db.close();

    }



    @Background
    public void insertIntoDatabase( GridWrapper gs ){


        int[][]grid = gs.getGrid(); // get the grid from event
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(grid);
            byte[] blob = stream.toByteArray();

            insertGrid(blob );

        }catch( IOException e ){

        }

      //  getFromDatabase();

    }

    @Background
    public void getFromDatabase(){

        try {

            SQLiteDatabase db = myDb.getWritableDatabase();
            String query = "SELECT  * FROM " + myDb.DB_TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            byte[] blob = cursor.getBlob(1);

            ByteArrayInputStream bStream = new ByteArrayInputStream(blob);
            ObjectInputStream oStream = new ObjectInputStream(bStream);
            int[][] dSerialize = (int[][]) oStream.readObject();
            Log.d("HERE", "%d" + dSerialize[0][0]);

        }catch( Exception e ){

            Log.d("humm", e.toString());

        }


    }

}