package com.example.localadmin.g11_ajh265_kex496.rest;

import android.util.Log;
import android.view.View;

import com.example.localadmin.g11_ajh265_kex496.R;
import com.example.localadmin.g11_ajh265_kex496.UI.GridAdapter;
import com.example.localadmin.g11_ajh265_kex496.constraint.ConstraintHandler;
import com.example.localadmin.g11_ajh265_kex496.constraint.LogicTank;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.Tank;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by localadmin on 11/10/15.
 */

@EBean
public class Controller {

    @RestService
    BulletZoneRestClient restClient;

    @Bean
    RestClientPoller myPoller;

    @Bean
    UIUpdate myUIUpdate;

    @Bean
    ConstraintHandler myConstraintHandler;


    private String TAG = "TankClientActivity";


    public Controller( ){

    }

    public void implement( View myGridView ){

        myPoller.addObserver( myUIUpdate);
        myUIUpdate.join( myGridView );
        myPoller.startPoll();

    }


    public void clicked( View v ){

        if( v.getId() == R.id.buttonFire ){
            fire();
        }

        else {

            if (v.getId() == R.id.buttonTurnLeft || v.getId() == R.id.buttonTurnRight) {

                turn(v);
            }

            else{
                move(v);

            }

        }
        // SystemClock.sleep(500);
    }



    @Background
    public void turn( View v ) {


        try {

            int dir = 0;

            if( v.getId() == R.id.buttonTurnLeft ){

               dir = myConstraintHandler.turnLeft();

            }else{

                dir = myConstraintHandler.turnRight();

            }

            byte b = (byte )dir;
            try {

                restClient.turn( myConstraintHandler.getTankId(), b);

            }catch (Exception e) {

                Log.d(TAG, "FAILED");
            }


        } catch (Exception e) {

        }



    }

    @Background
    public void fire( ) {

        try {
            restClient.fire(myConstraintHandler.getTankId());
        }catch (Exception e) {
            Log.d(TAG, "FAILED");
        }

    }


    @Background
    public void move( View v ) {

        int i = 0;
        if( v.getId() == R.id.buttonUp ){
            i = 0;
        }
        if( v.getId() == R.id.buttonLeft) {
            i = 6;
        }
        if( v.getId() == R.id.buttonRight ){
            i = 2;
        }
        if( v.getId() == R.id.buttonDown ){
            i = 4;
        }

        byte b = (byte )i;
        try {

                if( myConstraintHandler.move( i ))
                restClient.move(myConstraintHandler.getTankId(), b);

        }catch (Exception e) {


        }

    }


}
