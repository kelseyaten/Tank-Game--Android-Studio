package com.example.localadmin.g11_ajh265_kex496;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.GridView;

import com.example.localadmin.g11_ajh265_kex496.UI.GridAdapter;
import com.example.localadmin.g11_ajh265_kex496.database.DBHelper;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.Tank;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@EActivity
public class ReplayActivity extends AppCompatActivity {

    private static DBHelper myDb;

    String val = "";

    @Bean
    GridAdapter myGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);

    }

    @AfterViews
    protected void afterViewInjection() {


        //Log.d("AGAS", "%d" +);



    }


    @Click( {R.id.fast, R.id.slow, R.id.med})
    void onClick( View v ) {



        if( v.getId() == R.id.fast ){

            play( 100 );

        }
        if( v.getId() == R.id.med){

            play( 200 );
        }
        if(  v.getId() == R.id.slow ){

            play( 300 );
        }


        View butt = (View) findViewById(R.id.fast);
        butt.setVisibility(View.INVISIBLE);


        View butt2 = (View) findViewById(R.id.med);
        butt2.setVisibility(View.INVISIBLE);


        View butt3 = (View) findViewById(R.id.slow);
        butt3.setVisibility(View.INVISIBLE);



    }



    @Background
    public void play( int speed){

        myGridAdapter.setTankID(Tank.getMyLogicTank().getId());


        for( int x = 0; x <TankClientActivity.myReplayStructure.getSize(); x ++ ){

            updateDisplay(x);
            SystemClock.sleep(speed);

        }


    }


    @org.androidannotations.annotations.UiThread
    public void updateDisplay( int index ){

        ((GridView)findViewById(R.id.gridView222)).setAdapter(myGridAdapter);
        myGridAdapter.updateList(TankClientActivity.myReplayStructure.getIndex(index));
        myGridAdapter.notifyDataSetChanged();


    }









}
