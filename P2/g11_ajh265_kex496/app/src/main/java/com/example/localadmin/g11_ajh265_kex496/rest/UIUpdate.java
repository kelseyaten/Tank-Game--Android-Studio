package com.example.localadmin.g11_ajh265_kex496.rest;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by localadmin on 11/10/15.
 */
@EBean
public class UIUpdate implements Observer {


    private long tankId;
    private static final String TAG = "TankClientActivity";

    @RestService
    BulletZoneRestClient restClient;


    private GridWrapper grid = new GridWrapper();

    @Bean
    GridUpdater myUpdater;



    public UIUpdate ( ){

    }

    public long join( View myGridView ){

        joinAsync();
        SystemClock.sleep(500);
        getField();
        SystemClock.sleep(500);


        myUpdater.init(myGridView, tankId, grid );

        return tankId;

    }



    @Background
    void getField() {
        try {
            grid = restClient.grid();

        } catch (Exception e) {

        }
    }




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
        myUpdater.updateDisplay( grid );

    }
}