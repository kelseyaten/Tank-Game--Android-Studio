package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * Created by localadmin on 10/31/15.
 */
public abstract class CellObject {

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;

    public abstract ImageView getMyView();
    public abstract void setMyView( ImageView v );

    public abstract ImageView display( Context c, GridLayout g, int size, long id );

    public abstract int updateDisplay( long paramid  );


}
