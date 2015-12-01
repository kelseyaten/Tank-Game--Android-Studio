package com.example.localadmin.g11_ajh265_kex496.rest;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;


import com.example.localadmin.g11_ajh265_kex496.constraint.BulletObserver;
import com.example.localadmin.g11_ajh265_kex496.database.GridWrapperDatab;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * has access to resclient methods join and getfield. when triggered by the poller it calls the
 * gridupdater to do its thing.
 * Created by localadmin on 11/10/15.
 */
@EBean
public class UIUpdate extends Observable implements Observer  {


    private long tankId;
    private static final String TAG = "TankClientActivity";

    @RestService
    BulletZoneRestClient restClient;




    private GridWrapper grid = new GridWrapper();

    public static GridWrapperDatab gridUpdater = new GridWrapperDatab();

    @Bean
    GridUpdater myUpdater;

    public static BulletObserver myBulletObserver = new BulletObserver();


    /**
     *
     * default constructor
     */
    public UIUpdate ( ){

        gridUpdater.setMyWrapper(grid);
      this.addObserver( myBulletObserver );
    }

    public static GridWrapperDatab getmyGridWrapperDatab(){

        return gridUpdater;
    }


    /**
     *
     * joins the game
     *
     * @param myGridView the game field to display
     * @return the players tanks id
     */
    public long join( View myGridView ){

        joinAsync();
        SystemClock.sleep(500);
        getField();
        SystemClock.sleep(500);


        myUpdater.init(myGridView, tankId, grid );

        return tankId;

    }


    /**
     *
     * updates the gamefield grid
     */

    @Background
    void getField() {
        try {

            grid = restClient.grid();
            setChanged();
            notifyObservers( grid );



        } catch (Exception e) {

        }
    }


    /**
     *
     * joins the game
     *
     */
    @Background
    void joinAsync() {
        try {
            tankId = restClient.join().getResult();
            Log.d(TAG, "tankId is " + tankId);
        } catch (Exception e) {

            Log.d(TAG, "FAILED JOIN");
        }
    }


    @Override
    public void update(Observable observable, Object data) {

        getField();
        myUpdater.updateDisplay(grid);

        gridUpdater.setMyWrapper(grid);
        gridUpdater.notifyme();



    }
}