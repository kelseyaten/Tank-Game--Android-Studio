package com.example.localadmin.g11_ajh265_kex496;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.localadmin.g11_ajh265_kex496.rest.Controller;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;


@EActivity
public class TankClientActivity extends AppCompatActivity {



    @Bean
    Controller myController;


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

    }

    @Click({R.id.buttonLeft, R.id.buttonRight, R.id.buttonUp, R.id.buttonDown, R.id.buttonFire, R.id.buttonTurnLeft, R.id.buttonTurnRight})
    void onClick( View v ) {

        myController.clicked( v );

    }




}