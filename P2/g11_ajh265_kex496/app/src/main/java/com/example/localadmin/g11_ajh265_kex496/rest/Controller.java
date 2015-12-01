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
import org.junit.Test;

/**
 *
 * controls all movements of tank ( calls move fire turn etc. )
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


    public Controller( ) {


    }
    /**
     *
     * sets up the contorller
     * @param myGridView sets up controller w/ particular gridview
     */

    public void implement( View myGridView ){

        myPoller.addObserver( myUIUpdate);
        myUIUpdate.join( myGridView );
        myPoller.startPoll();

    }


    /**
     *
     * decides what to do when button is clicked
     * @param v what view was clicked
     */

    public void clicked( View v ){

        if( v.getId() == R.id.buttonFire ){
            fire();
        }

        else {

            move(v);

        }
        // SystemClock.sleep(500);
    }




    /**
     *
     * fires the tank
     */
    @Background
    public void fire( ) {

        try {


                if( myConstraintHandler.canFire() )
                restClient.fire(myConstraintHandler.getTankId());

        }catch (Exception e) {
            Log.d(TAG, "FAILED");
        }

    }


    /**
     *
     * moves the tank
     * @param v the button that was clicked
     */
    @Background
    public void move( View v ) {

        action myAction = null;


        if( v.getId() == R.id.buttonUp ){
            myAction = action.UP;
        }
        if( v.getId() == R.id.buttonLeft) {
            myAction = action.LEFT;
        }
        if( v.getId() == R.id.buttonRight ){
            myAction = action.RIGHT;
        }
        if( v.getId() == R.id.buttonDown ){
            myAction = action.DOWN;
        }

        int i = getEnumValue( myAction );
        byte b = (byte )i;
        try {
                if( myConstraintHandler.move(i)){
                    restClient.move(myConstraintHandler.getTankId(), b);
                }else{

                    restClient.turn(  myConstraintHandler.getTankId(), b );
                }


        }catch (Exception e) {


        }

    }


    /**
     * gets int value from enum
     * @param m the enum
     * @return the value
     */

    public int getEnumValue( action m ){

       if( m == action.UP ){

           return 0;
       }else if( m == action.DOWN ){

           return 4;
       }else if( m == action.LEFT ){

           return 6;
       }else if( m == action.RIGHT ){
           return 2;
       }

        return -1;
    }


    public enum action {
      UP, DOWN, LEFT, RIGHT
    }





    @Test
    public void test(){


        int i = 0;
        byte b = (byte )i;
        try {
            if( myConstraintHandler.move(i))
                restClient.move(myConstraintHandler.getTankId(), b);

        }catch (Exception e) {


        }


        try {

            int dir = 0;

            dir = myConstraintHandler.turnLeft();

            b = (byte )dir;
            try {

                restClient.turn( myConstraintHandler.getTankId(), b);

            }catch (Exception e) {

                Log.d(TAG, "FAILED");
            }


        } catch (Exception e) {

        }


        if( myConstraintHandler.canFire() )
            restClient.fire(myConstraintHandler.getTankId());


    }


}
