package com.example.localadmin.g11_ajh265_kex496;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.localadmin.g11_ajh265_kex496.rest.Controller;
import com.example.localadmin.g11_ajh265_kex496.sensor.ShakeListener;
import com.example.localadmin.g11_ajh265_kex496.sensor.ShakeListenerManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;


@EActivity
public class TankClientActivity extends AppCompatActivity implements ShakeListener {



    @Bean
    Controller myController;


    @Bean
    ShakeListenerManager sd;


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

    }


    @Click({R.id.buttonLeft, R.id.buttonRight, R.id.buttonUp, R.id.buttonDown, R.id.buttonFire, R.id.buttonTurnLeft, R.id.buttonTurnRight})
    void onClick( View v ) {

        myController.clicked( v );

    }


    @Override
    public void onShake() {

       myController.fire();

    }
}