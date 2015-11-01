package com.example.localadmin.g11_ajh265_kex496;

import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.localadmin.g11_ajh265_kex496.factory.BattleField;
import com.example.localadmin.g11_ajh265_kex496.factory.CellObject;
import com.example.localadmin.g11_ajh265_kex496.factory.EntityFactory;
import com.example.localadmin.g11_ajh265_kex496.rest.BulletZoneRestClient;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;

@EActivity
public class TankClientActivity extends AppCompatActivity {

    private static final String TAG = "TankClientActivity";

    @RestService
    BulletZoneRestClient restClient;

    private long tankId = -1;
    GridWrapper grid;
    boolean gameRunning = true;

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

        joinAsync();
        SystemClock.sleep(500);


        GridLayout myGridLayout = (GridLayout)findViewById(R.id.gridLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        EntityFactory[][] myArray = new EntityFactory[16][16];



        EntityFactory myFactory = new EntityFactory();

        BattleField myField = new BattleField( grid );



    }

    @Background
    void joinAsync() {
        try {
            tankId = restClient.join().getResult();
            Log.d(TAG, "tankId is " + tankId);
            startPoll();
        } catch (Exception e) {

            Log.d(TAG, "FAILED");
        }
    }


    @Background
    void getField() {
        try {
            grid = restClient.grid();
            Log.d(TAG, "Got Grid" );
        } catch (Exception e) {

        }
    }


    @Background
    void startPoll() {

        while(gameRunning){

            getField();
            SystemClock.sleep(100);
            updateDisplay();

         }
    }

    @Background
    public void updateDisplay(){

        grid.getGrid();
        for( int x = 0; x <  grid.getGrid().length ; x ++  ){
            for( int y = 0; y < grid.getGrid()[0].length; y ++ ){

            }
        }
    }

}