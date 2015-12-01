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

import java.util.Observable;

/**
 *
 * continuously polls and notifies its observers that it has polled
 * Created by localadmin on 11/9/15.
 */
@EBean
public class RestClientPoller extends Observable {




    private boolean gameRunning = true;

    public RestClientPoller( ){

        startPoll();

    }

    /**
     * starts the polling
     */
    @Background
    void startPoll() {
        while(gameRunning){

            SystemClock.sleep(100);
            setChanged();
            notifyObservers();

        }
    }



}
