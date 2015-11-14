package com.example.localadmin.g11_ajh265_kex496.rest;

import android.util.Log;
import android.view.View;

import com.example.localadmin.g11_ajh265_kex496.R;
import com.example.localadmin.g11_ajh265_kex496.UI.GridAdapter;
import com.example.localadmin.g11_ajh265_kex496.factory.LogicTank;
import com.example.localadmin.g11_ajh265_kex496.factory.Tank;
import com.example.localadmin.g11_ajh265_kex496.sensor.ShakeListener;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

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
    protected GridAdapter mGridAdapter;

    @Bean
    RestClientPoller myPoller;

    @Bean
    UIUpdate myUIUpdate;



    private static final String TAG = "TankClientActivity";
    private long tankId;
    private int direction;

    private LogicTank myLogicTank;



    public Controller( ){

        tankId = -1;
    }

    public void implement( View myGridView ){

        myPoller.addObserver( myUIUpdate);
        tankId = myUIUpdate.join( myGridView );
        myPoller.startPoll();
        myLogicTank = Tank.getMyLogicTank();

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

            Log.d(TAG, "TURNED");
            direction = (int) myLogicTank.getDirection();
            Log.d(TAG, "TURNED");


            int dir = 0;

            if( v.getId() == R.id.buttonTurnLeft ){

                switch( direction ){

                    case 2:
                        dir = 0;
                        break;
                    case 0:
                        dir = 6;
                        break;
                    case 6:
                        dir = 4;
                        break;
                    case 4:
                        dir = 2;
                        break;

                }


            }else{

                switch( direction ){

                    case 2:
                        dir = 4;
                        break;
                    case 4:
                        dir = 6;
                        break;
                    case 6:
                        dir = 0;
                        break;
                    case 0:
                        dir = 2;
                        break;

                }


            }

            byte b = (byte )dir;
            try {
                Log.d(TAG, "TURNED");
                restClient.turn(tankId, b);

            }catch (Exception e) {

                Log.d(TAG, "FAILED");
            }


        } catch (Exception e) {

        }



    }

    @Background
    public void fire( ) {

        try {
            restClient.fire(tankId);
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

            direction = (int) myLogicTank.getDirection();

            if( ( direction == 0  ||  direction == 4 ) && ( i == 0  ||  i == 4 ) )
                restClient.move(tankId, b);

            if( ( direction == 2  ||  direction == 6 ) && ( i == 2  ||  i == 6 ) )
                restClient.move(tankId, b);

        }catch (Exception e) {


        }

    }


}
