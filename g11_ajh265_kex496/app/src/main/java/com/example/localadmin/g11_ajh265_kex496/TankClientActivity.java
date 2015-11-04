package com.example.localadmin.g11_ajh265_kex496;

import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.localadmin.g11_ajh265_kex496.UI.GridAdapter;
import com.example.localadmin.g11_ajh265_kex496.factory.BattleField;
import com.example.localadmin.g11_ajh265_kex496.factory.CellObject;
import com.example.localadmin.g11_ajh265_kex496.factory.EntityFactory;
import com.example.localadmin.g11_ajh265_kex496.rest.BulletZoneRestClient;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;

@EActivity
public class TankClientActivity extends AppCompatActivity {

    private static final String TAG = "TankClientActivity";

    @RestService
    BulletZoneRestClient restClient;

    private long tankId = -1;
    private GridWrapper grid;
    private boolean gameRunning = true;


    @Bean
    protected GridAdapter mGridAdapter;


    private BattleField myField;


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
        getField();
        SystemClock.sleep(500);

        mGridAdapter.updateList(grid);
        mGridAdapter.setTankID(tankId);
        mGridAdapter.setContext(getApplicationContext());
        GridView myGridView = (GridView)findViewById(R.id.gridView);
        myGridView.setAdapter(mGridAdapter);



        ((ImageButton)findViewById(R.id.buttonLeft)).setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.buttonRight)).setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.buttonDown)).setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.buttonUp)).setOnClickListener(ClickListener);
        ((ImageButton)findViewById(R.id.buttonFire)).setOnClickListener(ClickListener);


        startPoll();
    }


    private View.OnClickListener ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int selected_item = Integer.parseInt((String)v.getTag());

                if(selected_item != 5 ){
                    move( selected_item );
                }else{
                    fire();
                }
          //  SystemClock.sleep(500);
        }
    };


    @Background
    void fire( ) {

        try {
            restClient.fire(tankId);
        }catch (Exception e) {
            Log.d(TAG, "FAILED");
        }

    }



        @Background
    void move( int dir ) {

        int i = 0;

        if( dir == 3 ){
            i = 0;
        }
        if( dir == 2 ) {
            i = 6;
        }
        if( dir == 1 ){
            i = 2;
        }
        if( dir == 4 ){
            i = 4;
        }

        byte b = (byte )i;
        try {
            restClient.move(tankId, b);

        }catch (Exception e) {


        }

    }


    @Background
    void joinAsync() {
        try {
            tankId = restClient.join().getResult();
            Log.d(TAG, "tankId is " + tankId);
        } catch (Exception e) {

            Log.d(TAG, "FAILED");
        }
    }


    @Background
    void getField() {
        try {
            grid = restClient.grid();

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

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mGridAdapter.updateList(grid);
                mGridAdapter.notifyDataSetChanged();

            }
        });
    }

}