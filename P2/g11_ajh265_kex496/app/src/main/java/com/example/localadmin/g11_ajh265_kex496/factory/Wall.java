package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * Created by localadmin on 10/31/15.
 */
public class Wall extends CellObject {

    public ImageView getMyView() {
        return myView;
    }

    public void setMyView(ImageView myView) {
        this.myView = myView;
    }

    ImageView myView;

    public Wall(){


    }

    public int display( long id ){

        return R.drawable.wall;

    }

    public int updateDisplay( long paramid  ){

        myView.setImageResource(R.drawable.wall);
        return R.drawable.wall;
    }



}
