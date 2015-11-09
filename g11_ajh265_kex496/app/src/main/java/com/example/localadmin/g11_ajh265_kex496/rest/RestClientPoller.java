package com.example.localadmin.g11_ajh265_kex496.rest;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.example.localadmin.g11_ajh265_kex496.UI.GridAdapter;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by localadmin on 11/9/15.
 */
@EBean
public class RestClientPoller {

    private long tankId;
    private boolean gameRunning = true;
    private static final String TAG = "TankClientActivity";
    private GridWrapper grid;

    @Bean
    protected GridAdapter mGridAdapter;

    @RestService
    BulletZoneRestClient restClient;

    Context myContext;

    public RestClientPoller( Context c ){
        myContext = c;
    }


    public long startPolling( View myGridView ){

        joinAsync();
        SystemClock.sleep(500);
        getField();
        SystemClock.sleep(500);

        mGridAdapter.updateList(grid);
        mGridAdapter.setTankID(tankId);

        ((GridView)myGridView).setAdapter(mGridAdapter);

        startPoll();

        return tankId;

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


    @org.androidannotations.annotations.UiThread
    public void updateDisplay(){

        mGridAdapter.updateList(grid);
        mGridAdapter.notifyDataSetChanged();

    }






}
