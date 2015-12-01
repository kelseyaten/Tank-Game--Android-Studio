package com.example.localadmin.g11_ajh265_kex496.database;

import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import java.util.Observable;

/**
 * Created by localadmin on 11/30/15.
 */
public class GridWrapperDatab extends Observable {


    public GridWrapperDatab() {

    }

    public GridWrapper getMyWrapper() {
        return myWrapper;
    }

    public void setMyWrapper(GridWrapper myWrapper) {
        this.myWrapper = myWrapper;
    }

    private GridWrapper myWrapper;


    public void notifyme( ){


        setChanged();
        notifyObservers();
    }







}
