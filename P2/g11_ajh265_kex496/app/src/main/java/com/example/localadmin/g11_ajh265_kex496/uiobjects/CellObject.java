package com.example.localadmin.g11_ajh265_kex496.uiobjects;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Observable;

/**
 * Created by localadmin on 10/31/15.
 */
public abstract class CellObject extends Observable{

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    int value;

    public abstract ImageView getMyView();
    public abstract void setMyView( ImageView v );
    public abstract int display( long id );
    public abstract int updateDisplay( long paramid  );


}
